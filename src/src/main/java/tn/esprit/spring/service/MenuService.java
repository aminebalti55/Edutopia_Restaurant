package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.MenuRepository;
import tn.esprit.spring.repository.RestaurantRepository;
import tn.esprit.spring.repository.SubscriptionRepository;
import tn.esprit.spring.serviceInterface.ImenuService;

import java.util.List;

@Service
@Component
public class MenuService implements ImenuService {

    // Injecter les repos des entités nécessaires
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    // ...

    public void sendPreferredDishEmail() {
        // Récupérer le restaurant unique
        Restaurant restaurant = restaurantRepository.findAll().get(0);

        // Récupérer le menu du jour pour ce restaurant
        Menu menu = menuRepository.findMenuByRestaurantAndTodayDate(restaurant);

        // Parcourir les abonnements pour ce restaurant
        List<Subscription> subscriptions = subscriptionRepository.findByRestaurant(restaurant);
        for (Subscription subscription : subscriptions) {
            // Si le plat préféré de l'abonnement est dans le menu, envoyer un email
            if (menu.getDishes().stream()
                    .anyMatch(dish -> dish.getName().equals(subscription.getPreferredDish()))) {
                String email = subscription.getUser().getEmail();
                String subject = "Votre plat préféré est disponible aujourd'hui !";
                String text = "Bonjour " + subscription.getUser().getFirstname() + ",\n\n"
                        + "Votre plat préféré '" + subscription.getPreferredDish() + "' est disponible aujourd'hui "
                        + "au restaurant " + restaurant.getRestaurantName() + ".\n\n"
                        + "Bon appétit !";

                // Envoyer l'email
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(email);
                mailMessage.setSubject(subject);
                mailMessage.setText(text);
                javaMailSender.send(mailMessage);
            }
        }
    }


}
