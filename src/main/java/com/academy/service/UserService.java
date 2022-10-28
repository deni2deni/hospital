package com.academy.service;

import com.academy.model.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> findAll();

    User findById(Integer id);

    User buildUser(String name, Integer role);

    List<User> findAllByRoleId(Integer roleId);

}
