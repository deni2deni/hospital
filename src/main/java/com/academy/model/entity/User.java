package com.academy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
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
