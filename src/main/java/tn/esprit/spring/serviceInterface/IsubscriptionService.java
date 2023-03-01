package tn.esprit.spring.serviceInterface;


import tn.esprit.spring.entities.Subscription;

import java.util.List;

public interface IsubscriptionService {
    void deleteSubscription(int id);
    Subscription createSubscription(Subscription subscription);
    Subscription getSubscriptionById(int id);
    List<Subscription> getAllSubscriptions();
    Subscription updateSubscription(Subscription subscription);
}
