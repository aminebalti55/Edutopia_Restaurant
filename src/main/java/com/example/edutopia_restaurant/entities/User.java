package com.example.edutopia_restaurant.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "User")

public class User implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private   int userId;

        @Column(name = "id_card")
        private    int idCard;

    private   String firstname;

    private   String lastname;

    private   String email;

        @Enumerated(EnumType.STRING)
        private  Role role;

        @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private  Set<Ratings> ratings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Report> reports;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Subscription> Subscriptions;

    }

