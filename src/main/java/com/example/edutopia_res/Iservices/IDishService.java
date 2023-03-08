package com.example.edutopia_res.Iservices;

import com.example.edutopia_res.entities.Category;
import com.example.edutopia_res.entities.Dish;
import com.example.edutopia_res.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IDishService {
    List<Dish> getAllDishes();
    Optional<Dish> getDishById(int dishId);
    Dish createDish(String name, String description, float price, MultipartFile image) throws IOException;
    Dish updateDish(int id, String name, String description, float price, MultipartFile image) throws IOException;

     void deleteDish(int id);

    Dish getHighestRatedDish();
    String generateRssFeed(Dish bestRatedDish);
    void sendRssNewsletter();
    void sendRssNewsletterWeekly();

    List<Dish> getRecommendedDishes(String username);
}
