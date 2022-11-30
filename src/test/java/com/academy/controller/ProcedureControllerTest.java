package com.academy.controller;

import com.academy.service.JournalService;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "DOCTOR", password = "1", roles = "DOCTOR")
class ProcedureControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private JournalService journalService;

    @Test
    void doProcedure() throws Exception {
        Mockito.doNothing().when(journalService).doProcedure(Mockito.isA(Integer.class));
        mvc.perform(MockMvcRequestBuilders.get("/procedure").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/doctorPage"));
    }

    @Test
    void doDischarge() throws Exception {
        Mockito.doNothing().when(journalService).discharge(Mockito.isA(String.class), Mockito.isA(String.class));
        mvc.perform(MockMvcRequestBuilders.get("/discharge").param("patientsUsername", "test").param("diagnosisName", "testD"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/doctorPage"));
    }
}