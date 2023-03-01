package tn.esprit.spring.service;



import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Dish;
import tn.esprit.spring.repository.DishRepository;
import tn.esprit.spring.serviceInterface.IDishService;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class DishService implements IDishService {


    @Autowired
    DishRepository dishRepository;

    public Dish createDish(String name, String description, float price, MultipartFile image) throws IOException {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setDescription(description);
        dish.setPrice(price);

        if (image != null) {
            String filename = image.getOriginalFilename();
            File file = new File("path/to/images/" + filename);
            if (file.exists()) {
                // Extract the file name from the original file name
                String imageName = FilenameUtils.getName(filename);
                // Save the image name in the dish object
                dish.setImage(imageName);
                // Save the image file to disk
                byte[] imageData = Files.readAllBytes(file.toPath());
                Files.write(Paths.get("path/to/images/" + imageName), imageData);
            } else {
                throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
            }
        }
        return dishRepository.save(dish);

    }


    /*public Dish updateDish(int id, Dish dish, MultipartFile image) throws IOException {

    }*/

    public void deleteDish(int id) {
        dishRepository.deleteById(id);
    }

    public List<Dish> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
        List<Dish> dishDtos = new ArrayList<>();

        for (Dish dish : dishes) {
            Dish dishDto = new Dish();
            dishDto.setId(dish.getId());
            dishDto.setName(dish.getName());
            dishDto.setDescription(dish.getDescription());
            dishDto.setPrice(dish.getPrice());
            String imageName = dish.getImage();
                if (imageName != null) {
                    dishDto.setImage(imageName);
                }
                dishDtos.add(dishDto);
            }
            return dishDtos;
          /*  if (dish.getImage() != null) {
                String imageUrl = "path/to/images/" + dish.getImage();
                byte[] imageData = null;
                try {
                    imageData = Files.readAllBytes(Paths.get(imageUrl));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (imageData != null) {
                    String base64Image = Base64.getEncoder().encodeToString(imageData);
                    dishDto.setImage("data:image/png;base64," + base64Image);
                }
            }

            dishDtos.add(dishDto);
        }

        return dishDtos;*/
    }



    public Optional<Dish> getDishById(int id) {
        return dishRepository.findById(id);
    }

    public Dish updateDish(int id, String name, String description, float price, MultipartFile image) throws IOException {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            Dish dish = optionalDish.get();
            dish.setName(name);
            dish.setDescription(description);
            dish.setPrice(price);

            if (image != null) {
                String filename = image.getOriginalFilename();
                File file = new File("path/to/images/" + filename);
                if (file.exists()) {
                    // Extract the file name from the original file name
                    String imageName = FilenameUtils.getName(filename);
                    // Save the image name in the dish object
                    dish.setImage(imageName);
                    // Save the image file to disk
                    byte[] imageData = Files.readAllBytes(file.toPath());
                    Files.write(Paths.get("path/to/images/" + imageName), imageData);
                } else {
                    throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
                }
            }

            return dishRepository.save(dish);
        } else {
return null ;        }
    }


}
