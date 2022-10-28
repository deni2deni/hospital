package com.academy.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer sum;
    @Column
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
