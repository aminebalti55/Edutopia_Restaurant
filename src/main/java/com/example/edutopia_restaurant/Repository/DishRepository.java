package com.example.edutopia_restaurant.Repository;

import com.example.edutopia_restaurant.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {


}