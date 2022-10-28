package com.academy.service;

import com.academy.model.entity.Diagnosis;

import java.util.List;

public interface DiagnosisService {

    List<Diagnosis> findAllDiagnosis();

    Diagnosis findById(Integer id);
}
