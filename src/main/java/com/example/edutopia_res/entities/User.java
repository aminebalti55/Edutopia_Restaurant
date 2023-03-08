package com.example.edutopia_res.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
        private   int  userId;

        @Column(name = "id_card")
        private    int idCard;

    private   String firstname;

    private   String lastname;

    private   String email;

    private   String password;

    private String username;

    private int loyaltyPoints;

    private int orderCount;

    @Enumerated(EnumType.STRING)
    private SpicinessLevel spicinessTolerance;

    private boolean isSubscribedToRss;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Badge> badges = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();


    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;







    public enum Gender {
        MALE, FEMALE
    }

    @Enumerated(EnumType.STRING)
        private  Role role;
        @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private  Set<Ratings> ratings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Report> reports;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Subscription subscription;



        }



