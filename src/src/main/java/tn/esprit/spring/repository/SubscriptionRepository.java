package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Restaurant;
import tn.esprit.spring.entities.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    @Query("SELECT s FROM Subscription s WHERE s.restaurant = :restaurant")
    List<Subscription> findByRestaurant(@Param("restaurant") Restaurant restaurant);

}