package com.academy.service.impl;

import com.academy.dto.DiagnosisDto;
import com.academy.mapper.DiagnosisMapper;
import com.academy.model.entity.Diagnosis;
import com.academy.model.repository.DiagnosisRepository;
import com.academy.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Override
    public List<DiagnosisDto> findAllDiagnosis() {
        return diagnosisRepository.findAll()
                .stream()
                .map(diagnosisMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Diagnosis findById(Integer id) {
        return diagnosisRepository.findById(id).get();
    }

}
