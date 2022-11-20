package com.academy.service.impl;

import com.academy.dto.JournalDto;
import com.academy.enums.ProcedureStatus;
import com.academy.enums.TreatmentTypeStatus;
import com.academy.enums.UserStatus;
import com.academy.mapper.JournalMapper;
import com.academy.model.entity.Journal;
import com.academy.model.repository.JournalRepository;
import com.academy.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {

    private final UserService userService;
    private final JournalRepository journalRepository;
    private final DiagnosisService diagnosisService;
    private final TreatmentService treatmentService;
    private final BillService billService;
    private final JournalMapper journalMapper;

    @Override
    public Journal buildJournal(Integer patientId, Integer doctorId, Integer diagnosisId, Integer treatmentTypeId, ProcedureStatus status) {
        return Journal.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .patient(userService.findById(patientId))
                .doctor(userService.findById(doctorId))
                .diagnosis(diagnosisService.findById(diagnosisId))
                .treatmentStatus(status.name())
                .treatment(treatmentService.createNewInstance(treatmentTypeId))
                .build();
    }

    @Override
    @Transactional
    public void save(Journal journal) {
        journalRepository.save(journal);
    }

    @Override
    public List<JournalDto> findAllByPatientId(Integer id) {
        return journalRepository.findAllByPatientId(id)
                .stream()
                .map(journalMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Journal findById(Integer id) {
        return journalRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void saveDiagnosisInJournal(Integer diagnosisId, Integer patientId, Integer treatmentTypeId) {
        var newJournal = buildJournal(patientId, 9, diagnosisId, treatmentTypeId, ProcedureStatus.PRESCRIPTION); //TODO need to change doctorId!!!
        var user = newJournal.getPatient();
        save(newJournal);
        userService.save(user);
    }

    @Override
    @Transactional
    public void doProcedure(Integer id, Integer doctorId) {
        var journal = findById(id);
        var newJournal = mapToProcedure(journal, 9); //TODO need to change doctorID!!!!
        newJournal.getTreatment().setTreatmentStatus(TreatmentTypeStatus.DONE.name());
        save(newJournal);
        var bill = billService.buildBill(newJournal.getTreatment().getTreatmentType().getPrice(), newJournal.getPatient());
        billService.save(bill);
    }

    @Override
    public Journal mapToProcedure(Journal journal, Integer doctorId) {
        return Journal.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .patient(journal.getPatient())
                .doctor(userService.findById(doctorId))
                .diagnosis(journal.getDiagnosis())
                .treatmentStatus(ProcedureStatus.PROCEDURE.name())
                .treatment(journal.getTreatment())
                .build();
    }

    @Override
    @Transactional
    public void discharge(Integer userId, Integer doctorId, Integer diagnosisId) {
        var user =userService.findById(userId);
        user.setStatus(UserStatus.DISCHARGED.name());
        var journal = buildJournal(user.getId(), doctorId, diagnosisId, null, ProcedureStatus.DISCHARGE);
        journal.setTreatment(treatmentService.createDischarge());
        userService.save(user);
        save(journal);
    }
}
