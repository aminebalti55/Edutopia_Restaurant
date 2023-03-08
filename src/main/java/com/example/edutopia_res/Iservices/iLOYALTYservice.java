package com.example.edutopia_res.Iservices;

import com.example.edutopia_res.entities.Order;
import com.example.edutopia_res.entities.User;

import java.util.List;

public interface iLOYALTYservice {

    boolean isUserEligibleForRewards(int userId);
    void awardLoyaltyPoints(int userId);
    List<String> getRewards(int userId);
    void assignBadgeToUser(int userId);
}
