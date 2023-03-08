package com.example.edutopia_res.Controllers;


import com.example.edutopia_res.Iservices.IsubscriptionService;
import com.example.edutopia_res.entities.Subscription;
import com.example.edutopia_res.entities.User;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    IsubscriptionService subscriptionService;
    @RolesAllowed("Admin")
    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(subscription);
    }

    @GetMapping("/{id}")
    public Subscription getSubscriptionById(@PathVariable int id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @PutMapping
    public Subscription updateSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.updateSubscription(subscription);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable int id) {
        subscriptionService.deleteSubscription(id);
    }



    @PostMapping("/Purchase")
    public ResponseEntity<?> subscribe(@RequestParam("userId") int userId,
                                       @RequestParam("packageType") Subscription.PackageType packageType,
                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        try {
            Subscription subscription = subscriptionService.purchasePackage(userId, packageType, endDate);
            return ResponseEntity.ok(subscription);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
