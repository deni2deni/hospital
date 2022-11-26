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
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private boolean enabled;
    @Column
    private boolean credentialsNonExpired;
    @Column
    private boolean accountNonExpired;
    @Column
    private boolean accountNonBlocked;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Bill> bills;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<PaymentCard> cards;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User() {

    }
}
