package tn.esprit.spring.service;



import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.DishRepository;
import tn.esprit.spring.repository.MenuRepository;
import tn.esprit.spring.repository.SubscriptionRepository;
import tn.esprit.spring.serviceInterface.IsubscriptionService;

import java.util.Date;
import java.util.List;


@Service

public class SubscriptionService implements IsubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    MenuRepository menuRepository;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

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
    /*@Scheduled(cron = "0 * * ? * *")*/
    public void notifSms(){
        List<Subscription>subscriptions=subscriptionRepository.findAll();
        List<Menu>menus=menuRepository.findAll();
        Date curDate = new Date();
        System.out.println("***********"+curDate);
        for(Subscription s : subscriptions){
            String favDish=s.getPreferredDish();
            User user =s.getUser();
                for (Menu m: menus){
                    int diffDays=(int)(curDate.getTime()-m.getCreatedAt().getTime()) / (1000 * 60 * 60 * 24);
                    System.out.println(diffDays);
                    if(diffDays==0){
                    for (Dish d: m.getDishes()){
                        if(d.getName().equals(favDish)){
                            AlertMessage alertMessage=new AlertMessage("your favorite dish  "+d.getName()+" in the restaurant today welcome back !");
                            Message.creator(new PhoneNumber(user.getPhoneNumber()),
                                    new PhoneNumber(phoneNumber),
                                    alertMessage.getMessage()).create();
                        }
                    }
                    }


                }
        }
    }



}

