package tn.esprit.spring.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentForm {
    private String nom;
    private String email;
    private String stripeToken;
    private Double montant;
}
