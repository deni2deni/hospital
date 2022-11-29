package com.academy.controller;

import com.academy.dto.DiagnosisDto;
import com.academy.dto.JournalCreateDto;
import com.academy.dto.UserDto;
import com.academy.model.entity.Role;
import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import com.academy.service.TreatmentTypeService;
import com.academy.service.UserService;
import com.academy.utils.SecurityUtil;
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
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @MockBean
    private SecurityUtil securityUtil;
    @MockBean
    private JournalService journalService;
    @MockBean
    private DiagnosisService diagnosisService;

    @Test
    void showDoctorPage() throws Exception {
        Mockito.doReturn(new UserDto()).when(userService).findByUsername(Mockito.isA(String.class));
        Mockito.doReturn("test").when(securityUtil).getUsername();
        Mockito.doReturn(1).when(userService).countPatientsWaitingForAdmission();
        mvc.perform(MockMvcRequestBuilders.get("/doctorPage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("doctorPage"));
    }

    @Test
    void admissionConfirm() throws Exception {
        Mockito.doReturn(new ArrayList<UserDto>()).when(userService).findAllByStatus(Mockito.isA(String.class));
        Mockito.doReturn(new ArrayList<DiagnosisDto>()).when(diagnosisService).findAllDiagnosis();
        mvc.perform(MockMvcRequestBuilders.get("/admissionConfirm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admissionConfirm"));
    }

    @Test
    void admissionConfirmProceed() throws Exception {
        Mockito.doNothing().when(journalService).saveAdmission(Mockito.isA(JournalCreateDto.class));
        mvc.perform(MockMvcRequestBuilders.post("/admissionConfirm"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/doctorPage"));
    }

    @Test
    void showAllPatients() throws Exception {
        Mockito.doReturn(new ArrayList<UserDto>()).when(userService).findAllByRole(Mockito.isA(Role.class));
        mvc.perform(MockMvcRequestBuilders.get("/patients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patients"));
    }
}