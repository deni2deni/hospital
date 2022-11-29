package com.academy.controller;

import com.academy.dto.PaymentCardDto;
import com.academy.dto.UserDto;
import com.academy.service.BillService;
import com.academy.service.JournalService;
import com.academy.service.PaymentCardService;
import com.academy.service.UserService;
import com.academy.utils.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
@WithMockUser(username = "USER", password = "1", roles = "USER")
class PatientControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @MockBean
    private BillService billService;
    @MockBean
    private SecurityUtil securityUtil;
    @MockBean
    private PaymentCardService paymentCardService;
    @MockBean
    private JournalService journalService;

    @Test
    void showPatientPage() throws Exception {
        Mockito.doReturn(new UserDto()).when(userService).findByUsername(Mockito.isA(String.class));
        Mockito.doReturn(1).when(billService).calculateUserBills(Mockito.isA(UserDto.class));
        Mockito.doReturn(false).when(paymentCardService).hasNoCard(Mockito.isA(UserDto.class));
        mvc.perform(MockMvcRequestBuilders.get("/patientPage"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patientPage"));
    }

    @Test
    void addPaymentCard() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/addCard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("addCard"));
    }

    @Test
    void savePaymentCard() throws Exception {
        Mockito.doNothing().when(paymentCardService).save(Mockito.isA(PaymentCardDto.class));
        mvc.perform(MockMvcRequestBuilders.post("/addCard"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/patientPage"));
    }

    @Test
    void moveToHospital() throws Exception {
        Mockito.doNothing().when(journalService).moveToHospital(Mockito.isA(String.class));
        mvc.perform(MockMvcRequestBuilders.get("/moveToHospital"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/patientPage"));
    }

    @Test
    void cancelAdmission() throws Exception {
        Mockito.doNothing().when(userService).cancelAdmission(Mockito.isA(String.class));
        mvc.perform(MockMvcRequestBuilders.get("/cancelAdmission"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/patientPage"));
    }

    @Test
    void payForBills() throws Exception {
        Mockito.doNothing().when(billService).payForBills(Mockito.isA(String.class));
        mvc.perform(MockMvcRequestBuilders.get("/bill"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/patientPage"));
    }

    @BeforeEach
    void setUp() {
        Mockito.doReturn("test").when(securityUtil).getUsername();
    }
}