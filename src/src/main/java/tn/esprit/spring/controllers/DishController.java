package tn.esprit.spring.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Dish;
import tn.esprit.spring.repository.DishRepository;
import tn.esprit.spring.serviceInterface.IDishService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/dishes")
    public class DishController {

    @Autowired
    IDishService dishService;


    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("price") float price,
                                           @RequestParam("image") MultipartFile image) throws IOException {
        Dish dish = dishService.createDish(name, description, price, image);
        return ResponseEntity.ok(dish);
    }






    @GetMapping("/")
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishService.getAllDishes();
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable int id) {
        Optional<Dish> dish = dishService.getDishById(id);
        if (dish.isPresent()) {
            return new ResponseEntity<>(dish.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable int id, @RequestParam String name, @RequestParam String description, @RequestParam float price, @RequestParam(required = false) MultipartFile image) throws IOException {
        Dish updatedDish = dishService.updateDish(id, name, description, price, image);
        Dish updatedDishDto = new Dish();
        updatedDishDto.setId(updatedDish.getId());
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
    @Autowired
    private DishRepository dishRepository;
    @GetMapping("/search")
    public List<Dish> searchDishes(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "maxPrice", required = false) Float maxPrice
    ) {
        if (name != null && maxPrice != null) {
            return dishRepository.findByNameContainingAndPriceLessThan(name, maxPrice);
        } else if (name != null) {
            return dishRepository.findByNameContaining(name);
        } else if (maxPrice != null) {
            return dishRepository.findByPriceLessThan(maxPrice);
        } else {
            return dishRepository.findAll();
        }
    }


    }

