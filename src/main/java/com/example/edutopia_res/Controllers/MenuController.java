package com.example.edutopia_res.Controllers;

import com.example.edutopia_res.Iservices.IDishService;
import com.example.edutopia_res.Iservices.ImenuService;
import com.example.edutopia_res.entities.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menus")

public class MenuController {
    @Autowired
    ImenuService MenuService;


    @PostMapping("/update")
    public ResponseEntity<String> updateMenu() {
        try {
            MenuService.updateMenu();
            return ResponseEntity.ok("Menu updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update menu: " + e.getMessage());
        }
    }
}
