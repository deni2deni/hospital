package com.academy.service.impl;

import com.academy.dto.TreatmentTypeDto;
import com.academy.mapper.TreatmentTypeMapper;
import com.academy.model.entity.TreatmentType;
import com.academy.model.repository.TreatmentTypeRepository;
import com.academy.service.TreatmentTypeService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class TreatmentTypeServiceImplTest {

    @Autowired
    private TreatmentTypeService treatmentTypeService;
    @MockBean
    private TreatmentTypeRepository treatmentTypeRepository;
    @Autowired
    private TreatmentTypeMapper treatmentTypeMapper;
    private List<TreatmentType> testList;

    @BeforeEach
    void setUp() {
        var treatmentDischarge = new TreatmentType();
        treatmentDischarge.setName("discharge");
        var treatment = new TreatmentType();
        treatment.setName("test");
        testList = new ArrayList<>();
        testList.add(treatmentDischarge);
        testList.add(treatment);

    }

    @Test
    void findAllTreatmentsTest() {
        Mockito.doReturn(testList).when(treatmentTypeRepository).findAll(Sort.by(Sort.Direction.ASC, "name"));
        var test = treatmentTypeService.findAllTreatments();
        assertEquals(1, test.size());

    }
}