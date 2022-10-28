package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
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
    @Column
    private Timestamp admission_date;
    @Column
    private Timestamp discharge_date;
    @ManyToOne
    @JoinColumn(name = "final_diagnosis_id")
    private Diagnosis finalDiagnosis;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Bill> bills;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<PaymentCard> cards;
    @OneToOne(mappedBy = "user")
    private Login login;

    public User() {

    }
}
