package com.academy.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String number;
    @Column
    private String expDate;
    @Column
    private String cardHolderName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
