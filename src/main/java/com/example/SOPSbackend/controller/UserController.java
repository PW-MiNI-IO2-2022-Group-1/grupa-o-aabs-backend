package com.example.SOPSbackend.controller;

import com.example.SOPSbackend.service.SecurityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final SecurityProvider securityService;

    @Autowired
    public UserController(SecurityProvider securityService) {
        this.securityService = securityService;
    }
}
