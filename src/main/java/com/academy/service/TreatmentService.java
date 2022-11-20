package com.academy.service;

import com.academy.model.entity.Treatment;

public interface TreatmentService {

    Treatment findById(Integer id);

    Treatment createNewInstance(Integer treatmentTypeId);

    Treatment createDischarge();
}
