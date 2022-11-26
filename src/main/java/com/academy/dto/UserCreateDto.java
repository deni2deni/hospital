package com.academy.dto;

import lombok.Data;

@Data
public class UserCreateDto {
    private String name;
    private String username;
    private String password;
    private String role;
}
