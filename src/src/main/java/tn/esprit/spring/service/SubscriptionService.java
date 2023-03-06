package tn.esprit.spring.service;



import com.stripe.model.billingportal.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Dish;
import tn.esprit.spring.entities.Menu;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.repository.DishRepository;
import tn.esprit.spring.repository.SubscriptionRepository;
import tn.esprit.spring.serviceInterface.IsubscriptionService;
import java.util.List;


@Service

public class SubscriptionService implements IsubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private SubscriptionService subscriptionService;

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

    public boolean checkIfDishInMenu(String preferredDish, Menu menu) {
        for (Dish dish : menu.getDishes()) {
            if (dish.getName().equals(preferredDish)) {
                return true;
            }
        }
        return false;
    }




}

