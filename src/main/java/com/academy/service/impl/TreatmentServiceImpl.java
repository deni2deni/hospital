package com.academy.service.impl;

import com.academy.enums.TreatmentTypeStatus;
import com.academy.model.entity.Treatment;
import com.academy.model.repository.TreatmentRepository;
import com.academy.service.TreatmentService;
import com.academy.service.TreatmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentTypeService treatmentTypeService;

    @Override
    public Treatment findById(Integer id) {
        return treatmentRepository.findById(id).get();
    }

    @Override
    public Treatment createNewInstance(Integer treatmentTypeId) {
        if (treatmentTypeId == null) {
            return null;
        }
        return Treatment.builder()
                .treatmentStatus(TreatmentTypeStatus.SCHEDULED.name())
                .treatmentType(treatmentTypeService.findById(treatmentTypeId))
                .build();
    }

    @Override
    public Treatment createDischarge() {
        return Treatment.builder()
                .treatmentStatus(TreatmentTypeStatus.DONE.name())
                .treatmentType(treatmentTypeService.findByName("discharge"))
                .build();
    }

    @Override
    public Treatment createAdmission() {
        return Treatment.builder()
                .treatmentStatus(TreatmentTypeStatus.DONE.name())
                .treatmentType(treatmentTypeService.findByName("admission"))
                .build();
    }

    @Override
    public Treatment createPrescription(Integer treatmentTypeId) {
        return Treatment.builder()
                .treatmentStatus(TreatmentTypeStatus.SCHEDULED.name())
                .treatmentType(treatmentTypeService.findById(treatmentTypeId))
                .build();
    }
}
