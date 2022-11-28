package com.academy.service.impl;

import com.academy.mapper.UserCreateMapper;
import com.academy.mapper.UserMapper;
import com.academy.model.entity.User;
import com.academy.model.repository.UserRepository;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCreateMapper userCreateMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        var user = new User();
        user.setName("test");

        userList = new ArrayList<>();
        userList.add(user);

        Mockito.doReturn(userList).when(repository).findAll();
    }

    @Test
    void findAll() {
        var users = userService.findAll();
        assertEquals("test", users.get(0).getName());
    }

    @Test
    void findById() {
    }

    @Test
    void buildUser() {
    }

    @Test
    void findAllByRole() {
    }

    @Test
    void findAllByRoleAndStatus() {
    }

    @Test
    void createNewUser() {
    }

    @Test
    void registerUser() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void cancelAdmission() {
    }

    @Test
    void countPatientsWaitingForAdmission() {
    }

    @Test
    void findAllByStatus() {
    }
}