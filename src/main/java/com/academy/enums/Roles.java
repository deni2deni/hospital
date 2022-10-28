package com.academy.enums;

import lombok.Getter;

@Getter
public enum Roles {
    PATIENT(1),
    DOCTOR(2),
    NURSE(3);

    private final Integer roleId;

    Roles(Integer roleId){
        this.roleId = roleId;
    }
}
