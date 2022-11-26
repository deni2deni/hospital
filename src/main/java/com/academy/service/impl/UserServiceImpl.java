package com.academy.service.impl;

import com.academy.dto.UserCreateDto;
import com.academy.dto.UserDto;
import com.academy.enums.UserStatus;
import com.academy.exception.UserExistException;
import com.academy.mapper.UserCreateMapper;
import com.academy.mapper.UserMapper;
import com.academy.model.entity.Role;
import com.academy.model.entity.User;
import com.academy.model.repository.UserRepository;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;
    private final UserCreateMapper userCreateMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
                .status("Active")
                .name(name)
                .build();
    }

    @Override
    public List<UserDto> findAllByRole(Role role) {
        return repository.findAllByRoles(role)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllByRoleAndStatus(Role role, String status) {
        return repository.findAllByRolesAndStatus(role, status)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createNewUser(String name, Integer role) {
        User user = buildUser(name, role);
        save(user);
    }

    @Override
    public void registerUser(UserCreateDto userCreateDto) {
        User user = userCreateMapper.toEntity(userCreateDto);
        if (repository.existsByUsername(user.getUsername())) {
            throw new UserExistException();
        }
        user.setEnabled(true);
        user.setAccountNonBlocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setStatus(UserStatus.NEW.name());
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Collections.singletonList(Role.ROLE_USER));
        }
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        save(user);
    }

    @Override
    public UserDto findByUsername(String username) {
        var user = repository.findByUsername(username);
        return userMapper.toDto(user);
    }

    @Override
    public void cancelAdmission(String username) {
        var user = repository.findByUsername(username);
        user.setStatus(UserStatus.NEW.name());
        save(user);
    }

    @Override
    public int countPatientsWaitingForAdmission() {
        return repository.countUserByStatus(UserStatus.WAITING_FOR_ADMISSION.name());
    }

    @Override
    public List<UserDto> findAllByStatus(String status) {
        return repository.findAllByStatus(status).stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
