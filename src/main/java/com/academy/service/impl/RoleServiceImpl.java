package com.academy.service.impl;

import com.academy.model.entity.Role;
import com.academy.model.repository.RoleRepository;
import com.academy.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findById(Integer id) {
        return repository.findById(id).get();
    }


}
