package com.academy.service;

import com.academy.model.entity.Treatment;

import java.util.List;

public interface TreatmentService {

    List<Treatment> findAllTreatments();

    Treatment findById(Integer id);
}
