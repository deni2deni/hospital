package com.academy.service.impl;

import com.academy.enums.TreatmentStatus;
import com.academy.model.entity.Journal;
import com.academy.model.repository.JournalRepository;
import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import com.academy.service.TreatmentService;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {

    private final UserService userService;
    private final JournalRepository journalRepository;
    private final DiagnosisService diagnosisService;
    private final TreatmentService treatmentService;

    @Override
    public Journal buildJournal(Integer patientId, Integer doctorId, Integer diagnosisId, Integer treatmentId) {
        return Journal.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .patient(userService.findById(patientId))
                .doctor(userService.findById(doctorId))
                .diagnosis(diagnosisService.findById(diagnosisId))
                .treatmentStatus(TreatmentStatus.SCHEDULED.name())
                .treatment(treatmentService.findById(treatmentId))
                .build();
    }

    @Override
    public void save(Journal journal) {
        journalRepository.save(journal);
    }

    @Override
    public List<Journal> findAllByPatientId(Integer id) {
        return journalRepository.findAllByPatientId(id);
    }

    @Override
    public Journal findById(Integer id) {
        return journalRepository.findById(id).get();
    }
}
