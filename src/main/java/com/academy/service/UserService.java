package com.academy.service;

import com.academy.dto.UserCreateDto;
import com.academy.dto.UserDto;
import com.academy.model.entity.Role;
import com.academy.model.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<UserDto> findAll();

    User findById(Integer id);

    List<UserDto> findAllByRole(Role role);

    List<UserDto> findAllByRoleAndStatus(Role role, String status);

    void registerUser(UserCreateDto userCreateDto);

    UserDto findByUsername(String username);

    void cancelAdmission(String username);

    int countPatientsWaitingForAdmission();

    List<UserDto> findAllByStatus(String status);

    Integer getRestrictedLevel(String username);

}
