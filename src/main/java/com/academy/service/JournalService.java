package com.academy.service;

import com.academy.model.entity.Journal;

import java.util.List;

public interface JournalService {

    Journal buildJournal(Integer patientId, Integer doctorId, Integer diagnosisId, Integer treatmentId);

    void save(Journal journal);

    List<Journal> findAllByPatientId(Integer id);

    Journal findById(Integer id);

}
