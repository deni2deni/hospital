package com.academy.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private RoleDto roleDto;
    private String status;
}
