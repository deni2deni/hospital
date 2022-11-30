package com.academy.model.entity;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_USER(0),
    ROLE_DOCTOR(2),
    ROLE_NURSE(1),
    ROLE_ADMIN(0);

    private final Integer restrictedLevel;

    Role(Integer restrictedLevel){
        this.restrictedLevel = restrictedLevel;
    }
}
