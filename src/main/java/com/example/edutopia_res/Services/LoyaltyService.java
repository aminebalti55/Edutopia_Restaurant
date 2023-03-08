package com.example.edutopia_res.Services;

import com.example.edutopia_res.Iservices.IuserService;
import com.example.edutopia_res.Iservices.iLOYALTYservice;
import com.example.edutopia_res.Repository.*;
import com.example.edutopia_res.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class LoyaltyService implements iLOYALTYservice {



    @Autowired
    UserRepository userRepository;

    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DishRepository dishRepository;



    public void awardLoyaltyPoints(int userId) {

        User user = userRepository.findById(userId).orElse(null);

        // Award loyalty points to the user and increment the dish order count
        user.setLoyaltyPoints(user.getLoyaltyPoints() + 5);
        user.setOrderCount(user.getOrderCount() + 1);

        // Update the user and dish in the database
        userRepository.save(user);
    }

    public boolean isUserEligibleForRewards(int userId) {
        // Retrieve the user
        User user = userRepository.findById(userId).orElse(null);

        // Check if the user has enough loyalty points to qualify for rewards
        return user.getLoyaltyPoints() >= 10;
    }

    public List<String> getRewards(int userId) {
        // Check if the user is eligible for rewards
        if (!isUserEligibleForRewards(userId)) {
            return Collections.emptyList();
        }

        // Generate a list of rewards based on the user's loyalty points
        List<String> rewards = new ArrayList<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user.getLoyaltyPoints() >= 10 && user.getLoyaltyPoints() < 20) {
            rewards.add("10% off your next order");
        } else if (user.getLoyaltyPoints() >= 20 && user.getLoyaltyPoints() < 30) {
            rewards.add("20% off your next order");
            rewards.add("Free soda with your next order");
        } else if (user.getLoyaltyPoints() >= 30) {
            rewards.add("50% off your next order");
            rewards.add("Free entree with your next order");
            rewards.add("Exclusive badge for loyal customers");
        }

        // Reset the user's loyalty points to zero
        user = userRepository.findById(userId).orElse(null);
        user.setLoyaltyPoints(0);
        userRepository.save(user);

        return rewards;
    }

    public void assignBadgeToUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getLoyaltyPoints() >= 100) {
            Badge badge = new Badge();
            badge.setBadgeType(BadgeType.GOLD);
            badge.setUser(user);
            badgeRepository.save(badge);
        } else if (user.getLoyaltyPoints() >= 50) {
            Badge badge = new Badge();
            badge.setBadgeType(BadgeType.SILVER);
            badge.setUser(user);
            badgeRepository.save(badge);
        } else if (user.getLoyaltyPoints() >= 25) {
            Badge badge = new Badge();
            badge.setBadgeType(BadgeType.BRONZE);
            badge.setUser(user);
            badgeRepository.save(badge);
        }


    }}
