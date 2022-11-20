package com.academy.service;

import com.academy.dto.JournalDto;
import com.academy.enums.ProcedureStatus;
import com.academy.model.entity.Journal;

import java.util.List;

public interface JournalService {

    Journal buildJournal(Integer patientId, Integer doctorId, Integer diagnosisId, Integer treatmentTypeId, ProcedureStatus status);

    void save(Journal journal);

    List<JournalDto> findAllByPatientId(Integer id);

    Journal findById(Integer id);

    void saveDiagnosisInJournal(Integer diagnosisId, Integer patientId, Integer treatmentId);

    void doProcedure(Integer id, Integer doctorId);

    Journal mapToProcedure(Journal journal, Integer doctorId);

    void discharge(Integer userId, Integer doctorId, Integer diagnosisId);

}
