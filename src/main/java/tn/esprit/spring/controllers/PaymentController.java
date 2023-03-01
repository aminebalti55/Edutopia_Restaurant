package tn.esprit.spring.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entities.PaymentForm;
import tn.esprit.spring.service.PaymentService;
import tn.esprit.spring.serviceInterface.IpaymentService;
import com.stripe.model.Charge;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;


@RestController

public class PaymentController {
   /* @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        // Configuration de l'API Stripe avec votre clé d'API
        Stripe.apiKey = "YOUR_API_KEY";

        // Création d'un objet de charge pour effectuer la transaction de paiement
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentRequest.getAmount()); // Montant à payer
        chargeParams.put("currency", "USD");
        chargeParams.put("source", paymentRequest.getToken()); // Jeton de carte de crédit
        Charge charge;
        try {
            charge = Charge.create(chargeParams);
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        // Mettre à jour la base de données pour refléter le paiement réussi
        paymentService.savePayment(paymentRequest, charge);

        return ResponseEntity.ok("Paiement effectué avec succès.");
    }*/
}
