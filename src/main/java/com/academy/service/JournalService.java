package com.academy.service;

import com.academy.dto.JournalCreateDto;
import com.academy.dto.JournalDto;
import com.academy.enums.ProcedureStatus;
import com.academy.model.entity.Journal;

import java.util.List;

public interface JournalService {

    Journal buildJournal(Integer patientId, Integer doctorId, Integer diagnosisId, Integer treatmentTypeId, ProcedureStatus status);

    void save(Journal journal);

    List<JournalDto> findAllByPatientId(Integer id);

    List<JournalDto> findAllByPatientUsername(String username);

    Journal findById(Integer id);

    void saveDiagnosisInJournal(JournalCreateDto journalCreateDto);

    void doProcedure(Integer id);

    Journal mapToProcedure(Journal journal);

    void discharge(String username, String diagnosisName);

    void moveToHospital(String username);

    void saveAdmission(JournalCreateDto journalCreateDto);

}
