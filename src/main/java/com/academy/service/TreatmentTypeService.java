package com.academy.service;

import com.academy.dto.TreatmentTypeDto;
import com.academy.model.entity.TreatmentType;

import java.util.List;

public interface TreatmentTypeService {

    List<TreatmentTypeDto> findAllTreatments();

    TreatmentType findById(Integer id);

    TreatmentType findByName(String name);
}
