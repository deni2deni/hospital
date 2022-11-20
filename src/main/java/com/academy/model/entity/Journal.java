package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "patient_user_id")
    private User patient;
    @ManyToOne
    @JoinColumn(name = "doctor_user_id")
    private User doctor;
    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;
    @Column
    private String treatmentStatus;

    public Journal() {
    }
}
