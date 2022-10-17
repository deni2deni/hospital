package com.academy.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
