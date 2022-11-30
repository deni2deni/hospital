package com.academy.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TreatmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer price;
    @Column
    private Integer restrictedLevel;
}
