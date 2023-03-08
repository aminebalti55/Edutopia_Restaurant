package com.example.edutopia_res.Controllers;


import com.example.edutopia_res.Iservices.IDishService;
import com.example.edutopia_res.Iservices.IuserService;
import com.example.edutopia_res.Repository.UserRepository;
import com.example.edutopia_res.entities.Category;
import com.example.edutopia_res.entities.Dish;
import com.example.edutopia_res.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/dishes")
    public class DishController {

    @Autowired
    IDishService dishService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IuserService userservice;

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("price") float price,
                                           @RequestParam("image") MultipartFile image) throws IOException {
        Dish dish = dishService.createDish(name, description, price, image);
        return ResponseEntity.ok(dish);
    }






    @GetMapping("/Getalldishes")
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/getdishbyid/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable int id) {
        Optional<Dish> dish = dishService.getDishById(id);
        if (dish.isPresent()) {
            return new ResponseEntity<>(dish.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable int id, @RequestParam String name, @RequestParam String description, @RequestParam float price, @RequestParam(required = false) MultipartFile image) throws IOException {
        Dish updatedDish = dishService.updateDish(id, name, description, price, image);
        Dish updatedDishDto = new Dish();
        //updatedDishDto.setId(updatedDish.getId());
        updatedDishDto.setName(updatedDish.getName());
        updatedDishDto.setDescription(updatedDish.getDescription());
        updatedDishDto.setPrice(updatedDish.getPrice());
        updatedDishDto.setImage(updatedDish.getImage());
        return ResponseEntity.ok(updatedDishDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable int id) {
        dishService.deleteDish(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }








    @GetMapping("/generate")
    public String generateRssFeed() {
        Dish bestRatedDish = dishService.getHighestRatedDish();
        return dishService.generateRssFeed(bestRatedDish);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendRssNewsletter() {
        List<User> subscribedUsers = userservice.getSubscribedUsers();

        // Check if there are subscribed users
        if (subscribedUsers.isEmpty()) {
            return ResponseEntity.badRequest().body("No subscribed users found.");
        }
       else  dishService.sendRssNewsletter();
        return ResponseEntity.ok("RSS newsletter sent successfully!");
    }


    @GetMapping("/{username}")
    public ResponseEntity<List<Dish>> getRecommendedDishes(@PathVariable String username) {
        List<Dish> recommendedDishes = dishService.getRecommendedDishes(username);
        return ResponseEntity.ok(recommendedDishes);
    }

}

