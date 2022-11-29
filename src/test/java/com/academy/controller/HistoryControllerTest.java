package com.academy.controller;

import com.academy.dto.JournalDto;
import com.academy.service.JournalService;
import com.academy.utils.DateFormatUtil;
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
class HistoryControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private JournalService journalService;
    @MockBean
    private SecurityUtil securityUtil;
    @Autowired
    private DateFormatUtil dateFormatUtil;

    @Test
    @WithMockUser(username = "doc", password = "1", roles = "DOCTOR")
    void showPatientsHistory() throws Exception {
        Mockito.doReturn(new ArrayList<JournalDto>()).when(journalService).findAllByPatientId(Mockito.isA(Integer.class));
        mvc.perform(MockMvcRequestBuilders.get("/history").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("history"));
    }

    @Test
    @WithMockUser(username = "user", password = "1", roles = "USER")
    void showCurrentPatientsHistory() throws Exception {
        Mockito.doReturn(new ArrayList<JournalDto>()).when(journalService).findAllByPatientUsername(Mockito.isA(String.class));
        Mockito.doReturn("test").when(securityUtil).getUsername();
        mvc.perform(MockMvcRequestBuilders.get("/myHistory"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("history"));
    }
}