package com.academy.service.impl;

import com.academy.model.entity.Treatment;
import com.academy.model.repository.TreatmentRepository;
import com.academy.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Override
    public List<Treatment> findAllTreatments() {
        return treatmentRepository.findAll();
    }

    @Override
    public Treatment findById(Integer id) {
        return treatmentRepository.findById(id).get();
    }
}
