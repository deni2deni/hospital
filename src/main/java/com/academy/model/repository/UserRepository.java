package com.academy.model.repository;

import com.academy.model.entity.Role;
import com.academy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByRolesAndStatus(Role roles, String status);

    List<User> findAllByRoles(Role roles);

    List<User> findAllByStatus(String status);

    User findByUsername(String username);

    boolean existsByUsername(String username);

    int countUserByStatus(String status);
}
