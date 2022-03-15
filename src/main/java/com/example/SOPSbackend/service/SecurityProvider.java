package com.example.SOPSbackend.service;

import com.example.SOPSbackend.model.User;

public interface SecurityProvider {
    boolean isAuthenticated(String login, String password);
}
