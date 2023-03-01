package tn.esprit.spring.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Payment;
import tn.esprit.spring.entities.PaymentForm;
import tn.esprit.spring.repository.PaymentRepository;
import tn.esprit.spring.serviceInterface.IpaymentService;

import java.util.HashMap;
import java.util.Map;


    @Service
    public class PaymentService implements IpaymentService {

        @Autowired
        private PaymentRepository paymentRepository;
/*
        public void savePayment(PaymentRequest paymentRequest, Charge charge) {
            Payment payment = new Payment();
            payment.setCurrency(charge.getCurrency());
            payment.setAmount(charge.getAmount());
            payment.setStripeChargeId(charge.getId());
            payment.setPaymentSourceToken(paymentRequest.getToken());
            paymentRepository.save(payment);
        }*/
    }




