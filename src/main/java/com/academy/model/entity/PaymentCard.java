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
    private String exp_date;
    @Column
    private String card_holder_name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
