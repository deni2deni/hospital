package com.academy.model.entity;

import lombok.Data;
import lombok.ToString;

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
    @ToString.Exclude
    private User user;
}
