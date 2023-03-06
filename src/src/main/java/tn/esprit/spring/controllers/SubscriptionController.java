package tn.esprit.spring.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.service.SubscriptionService;
import tn.esprit.spring.serviceInterface.IsubscriptionService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

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



    @Scheduled(cron = "0 * * ? * *")
    public void cronMethod() {

        List<Subscription> Subscriptions = subscriptionService.getAllSubscriptions();
        List<Integer> expirationSoonList = new ArrayList<>();
        for (Subscription o : Subscriptions) {
            Date expDate = o.getExpiritaionDate();
            Date currDate = new Date();
            int diffInDays = (int) ((expDate.getTime()-currDate.getTime())
                    / (1000 * 60 * 60 * 24));
            System.out.println(diffInDays);

            if (diffInDays == 7) {
                expirationSoonList.add(o.getSubscriptionId());
            }

        } System.out.println("list of Subscriptions that will expire in 7 days :"+expirationSoonList);
    }


}
