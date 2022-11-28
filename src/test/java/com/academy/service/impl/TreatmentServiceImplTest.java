package com.academy.service.impl;

import com.academy.enums.TreatmentTypeStatus;
import com.academy.model.entity.TreatmentType;
import com.academy.model.repository.TreatmentRepository;
import com.academy.service.TreatmentService;
import com.academy.service.TreatmentTypeService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class TreatmentServiceImplTest {

    @Autowired
    private TreatmentService treatmentService;
    @MockBean
    private TreatmentRepository treatmentRepository;
    @MockBean
    private TreatmentTypeService treatmentTypeService;

    @BeforeEach
    void setUp() {
        var treatmentType = new TreatmentType();
        Mockito.doReturn(treatmentType).when(treatmentTypeService).findByName(Mockito.isA(String.class));
        Mockito.doReturn(treatmentType).when(treatmentTypeService).findById(Mockito.isA(Integer.class));
    }

    @Test
    void createDischarge() {
        var treatment = treatmentService.createDischarge();
        assertEquals(TreatmentTypeStatus.DONE.name(), treatment.getTreatmentStatus());
    }

    @Test
    void createAdmission() {
        var treatment = treatmentService.createAdmission();
        assertEquals(TreatmentTypeStatus.DONE.name(), treatment.getTreatmentStatus());
    }

    @Test
    void createPrescription() {
        var treatment = treatmentService.createPrescription(1);
        assertEquals(TreatmentTypeStatus.SCHEDULED.name(), treatment.getTreatmentStatus());
    }
}