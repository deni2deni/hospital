package com.academy.dto;

import com.academy.model.entity.Role;
import lombok.Data;

@Data
public class UserCreateDto {
    private String name;
    private String username;
    private String password;
    private Role role;
}
