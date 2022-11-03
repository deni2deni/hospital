package com.academy.service.impl;

import com.academy.enums.UserStatus;
import com.academy.model.entity.User;
import com.academy.model.repository.UserRepository;
import com.academy.service.RoleService;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleService roleService;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
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
                .admissionDate(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Override
    public List<User> findAllByRoleId(Integer roleId) {
        return repository.findAllByRoleIdAndStatus(roleId, UserStatus.ACTIVE.name());
    }

    @Override
    public void discharge(User user) {
        user.setStatus(UserStatus.DISCHARGED.name());
        user.setDischargeDate(new Timestamp(System.currentTimeMillis()));
        repository.save(user);
    }
}
