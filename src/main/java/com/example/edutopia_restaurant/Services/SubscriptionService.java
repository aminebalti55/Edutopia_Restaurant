package com.example.edutopia_restaurant.Services;

import com.example.edutopia_restaurant.Iservices.IsubscriptionService;
import com.example.edutopia_restaurant.Repository.SubscriptionRepository;
import com.example.edutopia_restaurant.entities.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SubscriptionService implements IsubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription getSubscriptionById(int id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription updateSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(int id) {
        subscriptionRepository.deleteById(id);
    }
}
