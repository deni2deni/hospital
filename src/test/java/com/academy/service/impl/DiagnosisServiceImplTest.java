package com.academy.service.impl;

import com.academy.dto.DiagnosisDto;
import com.academy.mapper.DiagnosisMapper;
import com.academy.mapper.DiagnosisMapperImpl;
import com.academy.model.entity.Diagnosis;
import com.academy.model.repository.DiagnosisRepository;
import com.academy.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
public class DiagnosisServiceImplTest {

    @Autowired
    private DiagnosisService diagnosisService;
    @MockBean
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private DiagnosisMapper diagnosisMapper;

    @BeforeEach
    void setUp() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1);
        diagnosis.setName("test");
        DiagnosisDto diagnosisDto = diagnosisMapper.toDto(diagnosis);
        List<Diagnosis> diagnosisList = Collections.singletonList(diagnosis);

        Mockito.when(diagnosisRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(diagnosisList);
    }

    @Test
    public void findAllDiagnosisTest() {
        var diagnosis = diagnosisService.findAllDiagnosis();
        assertEquals("test", diagnosis.get(0).getName());
    }
}