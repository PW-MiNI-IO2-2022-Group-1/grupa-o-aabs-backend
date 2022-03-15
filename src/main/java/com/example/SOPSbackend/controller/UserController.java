package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.model.Person;
import com.example.SOPSbackend.model.User;
import com.example.SOPSbackend.service.PersonService;
import com.example.SOPSbackend.service.SecurityProvider;
import com.example.SOPSbackend.utils.LoginPayload;
import com.example.SOPSbackend.utils.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final SecurityProvider securityService;

    @Autowired
    public UserController(SecurityProvider securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/doctor/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginPayload payload) {
        if (securityService.isAuthenticated(payload.login, payload.password)) {
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password.");
    }
}
