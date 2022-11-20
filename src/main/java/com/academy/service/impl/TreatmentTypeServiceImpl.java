package com.academy.service.impl;

import com.academy.dto.TreatmentTypeDto;
import com.academy.mapper.TreatmentTypeMapper;
import com.academy.model.entity.TreatmentType;
import com.academy.model.repository.TreatmentTypeRepository;
import com.academy.service.TreatmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreatmentTypeServiceImpl implements TreatmentTypeService {

    private final TreatmentTypeRepository treatmentTypeRepository;
    private final TreatmentTypeMapper treatmentTypeMapper;

    @Override
    public List<TreatmentTypeDto> findAllTreatments() {
        return treatmentTypeRepository.findAll()
                .stream()
                .map(treatmentTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TreatmentType findById(Integer id) {
        return treatmentTypeRepository.findById(id).get();
    }

    @Override
    public TreatmentType findByName(String name) {
        return treatmentTypeRepository.findByName(name);
    }
}
