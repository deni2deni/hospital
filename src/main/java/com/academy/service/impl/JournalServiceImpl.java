package com.academy.service.impl;

import com.academy.dto.JournalCreateDto;
import com.academy.dto.JournalDto;
import com.academy.enums.ProcedureStatus;
import com.academy.enums.TreatmentTypeStatus;
import com.academy.enums.UserStatus;
import com.academy.exception.ForbiddenProcedureException;
import com.academy.mapper.JournalMapper;
import com.academy.model.entity.Journal;
import com.academy.model.entity.Role;
import com.academy.model.repository.JournalRepository;
import com.academy.model.repository.UserRepository;
import com.academy.service.*;
import com.academy.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JournalRepository journalRepository;
    private final DiagnosisService diagnosisService;
    private final TreatmentService treatmentService;
    private final BillService billService;
    private final JournalMapper journalMapper;
    private final SecurityUtil securityUtil;

    @Override
    public Journal buildJournal(Integer patientId, Integer doctorId, Integer diagnosisId, Integer treatmentTypeId, ProcedureStatus status) {
        return Journal.builder()
                .date(new Timestamp(System.currentTimeMillis()).toInstant())
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
         var a = journalRepository.findAllByPatientId(id)
                .stream()
                .map(journalMapper::toDto)
                .collect(Collectors.toList());
        return a;
    }

    @Override
    public List<JournalDto> findAllByPatientUsername(String username) {
        var user = userService.findByUsername(username);
        return findAllByPatientId(user.getId());
    }

    @Override
    public Journal findById(Integer id) {
        return journalRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void saveDiagnosisInJournal(JournalCreateDto journalCreateDto) {
        var newJournal = Journal.builder()
                .date(new Timestamp(System.currentTimeMillis()).toInstant())
                .patient(userRepository.findByUsername(journalCreateDto.getPatientsUsername()))
                .doctor(userRepository.findByUsername(securityUtil.getUsername()))
                .diagnosis(diagnosisService.findById(journalCreateDto.getDiagnosisId()))
                .treatmentStatus(ProcedureStatus.PRESCRIPTION.name())
                .treatment(treatmentService.createPrescription(journalCreateDto.getTreatmentTypeId()))
                .build();
        save(newJournal);
    }

    @Override
    @Transactional
    public void doProcedure(Integer id) {
        var journal = findById(id);
        if (journal.getTreatment().getTreatmentType().getName().equalsIgnoreCase("surgeon") && securityUtil.hasRole(Role.ROLE_NURSE)){
            throw new ForbiddenProcedureException();
        }
        var newJournal = mapToProcedure(journal);
        newJournal.getTreatment().setTreatmentStatus(TreatmentTypeStatus.DONE.name());
        save(newJournal);
        var bill = billService.buildBill(newJournal.getTreatment().getTreatmentType().getPrice(), newJournal.getPatient());
        billService.save(bill);
    }

    @Override
    public Journal mapToProcedure(Journal journal) {
        return Journal.builder()
                .date(new Timestamp(System.currentTimeMillis()).toInstant())
                .patient(journal.getPatient())
                .doctor(userRepository.findByUsername(securityUtil.getUsername()))
                .diagnosis(journal.getDiagnosis())
                .treatmentStatus(ProcedureStatus.PROCEDURE.name())
                .treatment(journal.getTreatment())
                .build();
    }

    @Override
    @Transactional
    public void discharge(String username) {
        var user = userRepository.findByUsername(username);
        user.setStatus(UserStatus.DISCHARGED.name());
        var journal = Journal.builder()
                .date(new Timestamp(System.currentTimeMillis()).toInstant())
                .patient(user)
                .doctor(userRepository.findByUsername(securityUtil.getUsername()))
                .diagnosis(diagnosisService.findByName("recovered"))
                .treatmentStatus(ProcedureStatus.DISCHARGE.name())
                .treatment(treatmentService.createDischarge())
                .build();
        userService.save(user);
        save(journal);
    }

    @Override
    @Transactional
    public void moveToHospital(String username) {
        var user = userRepository.findByUsername(username);
        user.setStatus(UserStatus.WAITING_FOR_ADMISSION.name());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveAdmission(JournalCreateDto journalCreateDto) {
        var patient = userRepository.findByUsername(journalCreateDto.getPatientsUsername());
        patient.setStatus(UserStatus.ACTIVE.name());
        userRepository.save(patient);
        var journal = Journal.builder()
                .date(new Timestamp(System.currentTimeMillis()).toInstant())
                .patient(patient)
                .doctor(userRepository.findByUsername(securityUtil.getUsername()))
                .diagnosis(diagnosisService.findById(journalCreateDto.getDiagnosisId()))
                .treatmentStatus(ProcedureStatus.ADMISSION.name())
                .treatment(treatmentService.createAdmission())
                .build();
        journalRepository.save(journal);
    }
}
