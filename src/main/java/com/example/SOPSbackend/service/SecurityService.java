package com.example.SOPSbackend.service;

import com.example.SOPSbackend.model.User;
import com.example.SOPSbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements SecurityProvider {
    private final UserRepository repository;

    public SecurityService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isAuthenticated(String login, String password) {
        return (repository.findByLoginPassword(login, password) != null);
    }
}
