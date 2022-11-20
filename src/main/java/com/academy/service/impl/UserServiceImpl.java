package com.academy.service.impl;

import com.academy.dto.UserDto;
import com.academy.enums.UserStatus;
import com.academy.mapper.UserMapper;
import com.academy.model.entity.User;
import com.academy.model.repository.UserRepository;
import com.academy.service.RoleService;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User buildUser(String name, Integer role) {
        return User.builder()
                .role(roleService.findById(role))
                .status("Active")
                .name(name)
                .build();
    }

    @Override
    public List<User> findAllByRoleId(Integer roleId) {
        return repository.findAllByRoleId(roleId);
    }

    @Override
    public List<User> findAllByRoleIdAndStatus(Integer roleId, String status) {
        return repository.findAllByRoleIdAndStatus(roleId, status);
    }

    @Override
    @Transactional
    public void createNewUser(String name, Integer role) {
        User user = buildUser(name, role);
        save(user);
    }
}
