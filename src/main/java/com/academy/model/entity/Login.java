package com.academy.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String status;
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
}
