package com.example.edutopia_res.Services;

import com.example.edutopia_res.Iservices.IreportService;
import com.example.edutopia_res.Repository.DishRepository;
import com.example.edutopia_res.Repository.ReportRepository;
import com.example.edutopia_res.entities.Dish;
import com.example.edutopia_res.entities.Report;
import com.example.edutopia_res.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class ReportService implements IreportService {
@Autowired
    DishRepository dishRepository;

    @Autowired
    ReportRepository reportRepository;


    public void reportDish(int dishId, User user, String title, String note) {
        Optional<Dish> optionalDish = dishRepository.findById(dishId);
        if (optionalDish.isPresent()) {
            Dish dish = optionalDish.get();

            // Check if the dish is already archived
            if (dish.isArchived()) {
                return;
            }

            // Check if the user has already reported this dish
            List<Report> reports = reportRepository.findByDishAndUser(dish, user);
            if (!reports.isEmpty()) {
                return;
            }

            // Create a new report
            Report report = new Report();
            report.setDish(dish);
            report.setUser(user);
            report.setTitle(title);
            report.setNote(note);
            report.setUrgent(false);
            report.setCreatedAt(new Date());
            report.setClosedAt(null);
            report.setClosed(false);

            // Save the report
            reportRepository.save(report);

            // Check if the dish has been reported three times
            if (reportRepository.countByDish(dish) >= 2) {
                // Archive the dish
                dish.setArchived(true);
                report.setUrgent(true);
                dishRepository.save(dish);
            }
        }
}}
