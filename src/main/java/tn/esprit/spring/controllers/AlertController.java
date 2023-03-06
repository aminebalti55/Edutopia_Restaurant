package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.SubscriptionService;

@RestController
@RequestMapping("/test")
public class AlertController {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/sms")
    public void sendAlertMessage() {

        subscriptionService.notifSms();

    }
}
