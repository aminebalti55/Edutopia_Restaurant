package tn.esprit.spring.serviceInterface;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Dish;

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
