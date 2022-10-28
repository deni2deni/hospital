package com.academy.service.impl;

import com.academy.model.entity.Diagnosis;
import com.academy.model.repository.DiagnosisRepository;
import com.academy.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    @Override
    public List<Diagnosis> findAllDiagnosis() {
        return diagnosisRepository.findAll();
    }

    @Override
    public Diagnosis findById(Integer id) {
        return diagnosisRepository.findById(id).get();
    }
}
