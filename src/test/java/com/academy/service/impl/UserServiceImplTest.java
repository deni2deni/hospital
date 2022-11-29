package com.academy.service.impl;

import com.academy.dto.UserCreateDto;
import com.academy.dto.UserDto;
import com.academy.enums.UserStatus;
import com.academy.mapper.UserCreateMapper;
import com.academy.mapper.UserMapper;
import com.academy.model.entity.Role;
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
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
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
    void findAllByRole() {
        userService = Mockito.mock(UserServiceImpl.class);
        Mockito.doReturn(userList).when(repository).findAllByRoles(Mockito.isA(Role.class));
        userService.findAllByRole(Role.ROLE_USER);
        Mockito.verify(userService, Mockito.times(1)).findAllByRole(Mockito.isA(Role.class));
    }

    @Test
    void findAllByRoleAndStatus() {
        userService = Mockito.mock(UserServiceImpl.class);
        Mockito.doReturn(userList).when(repository).findAllByRolesAndStatus(Mockito.isA(Role.class), Mockito.isA(String.class));
        userService.findAllByRoleAndStatus(Role.ROLE_USER, UserStatus.ACTIVE.name());
        Mockito.verify(userService, Mockito.times(1)).findAllByRoleAndStatus(Mockito.isA(Role.class), Mockito.isA(String.class));
    }

    @Test
    void registerUser() {
        userService = Mockito.mock(UserServiceImpl.class);
        Mockito.doReturn(false).when(repository).existsByUsername(Mockito.isA(String.class));
        Mockito.doReturn(null).when(repository).save(Mockito.isA(User.class));
        var userCreateDto = new UserCreateDto();
        userCreateDto.setPassword("1");
        userService.registerUser(userCreateDto);
        Mockito.verify(userService, Mockito.times(1)).registerUser(Mockito.isA(UserCreateDto.class));

    }

    @Test
    void cancelAdmission() {
        Mockito.doReturn(user).when(repository).findByUsername(Mockito.isA(String.class));
        userService.cancelAdmission("test");
        assertEquals(UserStatus.NEW.name(), user.getStatus());
    }
}