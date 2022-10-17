package com.academy.service;

import com.academy.model.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {
    List<Role> findAll();
}
