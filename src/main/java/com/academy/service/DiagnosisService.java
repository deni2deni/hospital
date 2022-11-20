package com.academy.service;

import com.academy.dto.DiagnosisDto;
import com.academy.model.entity.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    List<DiagnosisDto> findAllDiagnosis();

    Diagnosis findById(Integer id);

}
