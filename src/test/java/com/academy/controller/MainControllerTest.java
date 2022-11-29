package com.academy.controller;

import com.academy.dto.UserCreateDto;
import com.academy.model.entity.Role;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    @MockBean
    private SecurityUtil securityUtil;

    @Test
    void login() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    void showRegistrationPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration"));
    }

    @Test
    void registerUser() throws Exception {
        Mockito.doNothing().when(userService).registerUser(Mockito.isA(UserCreateDto.class));
        mvc.perform(MockMvcRequestBuilders.post("/registration"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/redirect"));
    }

    @Test
    @WithMockUser(username = "ADMIN", password = "1", roles = "ADMIN")
    void redirect() throws Exception {
        Mockito.doReturn(true).when(securityUtil).hasRole(Role.ROLE_ADMIN);
        mvc.perform(MockMvcRequestBuilders.get("/redirect"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/adminPage"));
    }
}