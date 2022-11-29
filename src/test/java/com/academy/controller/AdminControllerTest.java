package com.academy.controller;

import com.academy.config.WebSecurityConfig;
import com.academy.dto.DiagnosisDto;
import com.academy.service.DiagnosisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "ADMIN", password = "1", roles = "ADMIN")
class AdminControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private DiagnosisService diagnosisService;


    @Test
    void showAdminPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/adminPage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("adminPage"));
    }

    @Test
    void showAllDiagnosis() throws Exception {
        Mockito.doReturn(new ArrayList<DiagnosisDto>()).when(diagnosisService).findAllDiagnosis();
        mvc.perform(MockMvcRequestBuilders.get("/showDiagnosis"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("showDiagnosis"));
    }

    @Test
    void addNewDiagnosis() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/addDiagnosis"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/adminPage"));
    }
}