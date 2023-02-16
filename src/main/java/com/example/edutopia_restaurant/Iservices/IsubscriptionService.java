package com.example.edutopia_restaurant.Iservices;

import com.example.edutopia_restaurant.entities.Subscription;

import java.util.List;

public interface IsubscriptionService {
    void deleteSubscription(int id);
    Subscription createSubscription(Subscription subscription);
    Subscription getSubscriptionById(int id);
    List<Subscription> getAllSubscriptions();
    Subscription updateSubscription(Subscription subscription);
}
