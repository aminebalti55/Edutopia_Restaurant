package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.spring.service.SubscriptionService;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private int subscriptionId;
    @Future(message = "Expiration date must be in the future")
    private Date expiritaionDate;
    @Positive(message = "Price must be a positive number")
    private float price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")

    private Date createdAt= new Date();

    @Column(name = "preferred_dish")
    @NotBlank(message = "Preferred dish cannot be blank")
    private String preferredDish;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;





}
