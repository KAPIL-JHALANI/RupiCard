package com.example.TestProjectg.controller;

import com.example.TestProjectg.model.UserData;
import com.example.TestProjectg.service.GoogleSheetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private GoogleSheetsService googleSheetsService;

    @RequestMapping("home")  // open the UI for enter the details
    public String home(){
        return "home";
    }

    @PostMapping("/submit")  // submit and save the data
    public ResponseEntity<String> saveUserData(@RequestBody UserData userData) {
        try {
            googleSheetsService.saveUserData(userData);
            return ResponseEntity.ok("Data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving data: " + e.getMessage());
        }
    }
}
