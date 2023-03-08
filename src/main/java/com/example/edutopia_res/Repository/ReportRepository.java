package com.example.edutopia_res.Repository;

import com.example.edutopia_res.entities.Dish;
import com.example.edutopia_res.entities.Report;
import com.example.edutopia_res.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByDishAndUser(Dish dish, User user);
    long countByDish(Dish dish);

}