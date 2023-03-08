package com.example.edutopia_res.Controllers;

import com.example.edutopia_res.Iservices.IDishService;
import com.example.edutopia_res.Iservices.IratingService;
import com.example.edutopia_res.Iservices.IreportService;
import com.example.edutopia_res.Repository.UserRepository;
import com.example.edutopia_res.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/Reports")
public class ReportController {
    @Autowired
    IreportService Reportservice;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/{dishId}")
    public ResponseEntity<?> reportDish(@PathVariable("dishId") int dishId,
                                        @RequestParam("userId") int userId,
                                        @RequestParam("title") String title,
                                        @RequestParam("note") String note) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));


        Reportservice.reportDish(dishId, user, title, note);
        return ResponseEntity.ok().build();
    }
}
