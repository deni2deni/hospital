package com.academy.service;

import com.academy.dto.UserDto;
import com.academy.model.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<UserDto> findAll();

    User findById(Integer id);

    User buildUser(String name, Integer role);

    List<User> findAllByRoleId(Integer roleId);

    List<User> findAllByRoleIdAndStatus(Integer roleId, String status);

    void createNewUser(String name, Integer role);

}
