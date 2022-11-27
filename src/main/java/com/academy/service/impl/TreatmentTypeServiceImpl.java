package com.academy.service.impl;

import com.academy.dto.TreatmentTypeDto;
import com.academy.mapper.TreatmentTypeMapper;
import com.academy.model.entity.TreatmentType;
import com.academy.model.repository.TreatmentTypeRepository;
import com.academy.service.TreatmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
        var treatments = treatmentTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .map(treatmentTypeMapper::toDto)
                .collect(Collectors.toList());
        treatments.removeIf(treatmentTypeDto -> treatmentTypeDto.getName().equalsIgnoreCase("admission") ||
                treatmentTypeDto.getName().equalsIgnoreCase("discharge"));
        return treatments;
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
