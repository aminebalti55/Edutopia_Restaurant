package com.example.edutopia_res.Services;


import com.example.edutopia_res.Iservices.IDishService;
import com.example.edutopia_res.Iservices.IuserService;
import com.example.edutopia_res.Repository.DishRepository;
import com.example.edutopia_res.Repository.RatingsRepository;
import com.example.edutopia_res.Repository.ReportRepository;
import com.example.edutopia_res.Repository.UserRepository;
import com.example.edutopia_res.entities.*;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service

public class DishService implements IDishService {


    @Autowired
    DishRepository dishRepository;
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    RatingsRepository ratingsRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    IuserService userservice;
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
            dishDto.setDishId(dish.getDishId());
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



    public Dish getHighestRatedDish() {
        // Retrieve the dish with the highest score
        return dishRepository.findTopByOrderByScoreDesc();
    }
    public String generateRssFeed(Dish bestRatedDish) {
        // Create a new SyndFeed object
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");

        // Set the title and description of the feed
        feed.setTitle("Canteen Best Rated Dish");
        feed.setDescription("The best rated dish from the canteen.");
        feed.setLink("http://localhost:8086/Edutopia");

        // Create a new SyndEntry object for the best-rated dish
        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle(bestRatedDish.getName());
        entry.setLink("http://localhost:8086/Edutopia/dishes/" + bestRatedDish.getDishId());
       SyndContent description = new SyndContentImpl();

       description.setValue(bestRatedDish.getDescription());
       entry.setDescription(description);

        // Create a new SyndContent object for the image of the best-rated dish
        SyndContent imageContent = new SyndContentImpl();
        imageContent.setType("image/jpeg");
        imageContent.setValue(bestRatedDish.getImage());

        // Add the image content to the SyndEntry object
        entry.setContents(Collections.singletonList(imageContent));

        // Add the SyndEntry object to the feed
        feed.setEntries(Collections.singletonList(entry));

        // Generate the RSS feed as a string
        StringWriter writer = new StringWriter();
        SyndFeedOutput output = new SyndFeedOutput();
        try {
            output.output(feed, writer);
        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }

    public void sendRssNewsletter() {
        // Retrieve the highest-rated dish
        Dish bestRatedDish = getHighestRatedDish();
        List<User> subscribedUsers = userservice.getSubscribedUsers();

        // Generate the RSS feed that includes the details of the best-rated dish
        String rssFeed = generateRssFeed(bestRatedDish);

        for (User user : subscribedUsers) {
            if (user.isSubscribedToRss()) {

        String host = "smtp.gmail.com";
        int port = 587;
        String username = "ccandyxx1@gmail.com";
        String password = "rmddmtsaxxeqplwe";
                String recipient = user.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Canteen Best Rated Dish");
            message.setContent(rssFeed, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("RSS newsletter sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
            }
        }
    }

    @Scheduled(cron = "0 0 12 * * 1") // run at noon every Monday
    public void sendRssNewsletterWeekly() {
        sendRssNewsletter();
    }


    public List<Dish> getDishesByCategory(Category category) {
        if (category == null) {
            return dishRepository.findAll();
        } else {
            return dishRepository.findByCategory(category);
        }
    }

    public List<Dish> getRecommendedDishes(String username) {
        User user = userRepository.findByUsername(username);
        SpicinessLevel userSpicinessTolerance = user.getSpicinessTolerance();
        List<Dish> allDishes = dishRepository.findAll();
        List<Dish> recommendedDishes = new ArrayList<>();

        for (Dish dish : allDishes) {
            if (dish.getSpicinessLevel().ordinal() <= userSpicinessTolerance.ordinal()) {
                recommendedDishes.add(dish);
            }
        }

        return recommendedDishes;
    }


}




