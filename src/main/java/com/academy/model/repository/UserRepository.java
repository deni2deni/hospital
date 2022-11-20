package com.academy.model.repository;

import com.academy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByRoleIdAndStatus(Integer roleId, String status);

    List<User> findAllByRoleId(Integer roleId);
}
