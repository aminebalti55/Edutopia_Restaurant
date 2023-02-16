package com.example.edutopia_restaurant.Repository;

import com.example.edutopia_restaurant.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer> {
}