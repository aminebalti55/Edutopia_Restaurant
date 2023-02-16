package com.example.edutopia_restaurant.Iservices;

import com.example.edutopia_restaurant.entities.Dish;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IDishService {
    List<Dish> getAllDishes();
    Optional<Dish> getDishById(int dishId);
    Dish createDish(String name, String description, float price, MultipartFile image) throws IOException;
    Dish updateDish(int id, String name, String description, float price, MultipartFile image) throws IOException;

     void deleteDish(int id);
}
