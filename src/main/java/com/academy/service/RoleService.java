package com.academy.service;

import com.academy.model.entity.Role;

import java.util.List;


public interface RoleService {
    List<Role> findAll();

    Role findById(Integer id);
}
