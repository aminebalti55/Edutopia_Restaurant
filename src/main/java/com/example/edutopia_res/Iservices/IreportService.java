package com.example.edutopia_res.Iservices;

import com.example.edutopia_res.entities.User;

public interface IreportService {
    void reportDish(int dishId, User user, String title, String note);
}
