package com.academy.controller;

import com.academy.dto.DiagnosisDto;
import com.academy.dto.JournalCreateDto;
import com.academy.dto.TreatmentTypeDto;
import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import com.academy.service.TreatmentTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "doc", password = "1", roles = "DOCTOR")
class DiagnosisControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private DiagnosisService diagnosisService;
    @MockBean
    private JournalService journalService;
    @MockBean
    private TreatmentTypeService treatmentTypeService;

    @Test
    void makeDiagnosis() throws Exception {
        Mockito.doReturn(new ArrayList<DiagnosisDto>()).when(diagnosisService).findAllDiagnosis();
        Mockito.doReturn(new ArrayList<TreatmentTypeDto>()).when(treatmentTypeService).findAllTreatments();
        mvc.perform(MockMvcRequestBuilders.get("/diagnosis").param("patientsUsername", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("make_diagnosis"));
    }

    @Test
    void saveDiagnosisInJournal() throws Exception {
        Mockito.doNothing().when(journalService).saveDiagnosisInJournal(Mockito.isA(JournalCreateDto.class));
        mvc.perform(MockMvcRequestBuilders.post("/diagnosis"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/doctorPage"));
    }
}