package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Dish;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {




    List<Dish> findByNameContaining(String name);

    List<Dish> findByPriceLessThan(Float maxPrice);

    List<Dish> findByNameContainingAndPriceLessThan(String name, Float maxPrice);
}