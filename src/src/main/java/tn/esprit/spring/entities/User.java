package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

    private String phoneNumber;

        @Enumerated(EnumType.STRING)
        private  Role role;
@JsonIgnore
        @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
        private  Set<Ratings> ratings;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Report> reports;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Subscription> Subscriptions;
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private QueueEntry queueEntry;
    @Column(name = "position_in_queue")
    private Integer positionInQueue;

    public User(String bruce, String wayne, String s) {
    }
}

