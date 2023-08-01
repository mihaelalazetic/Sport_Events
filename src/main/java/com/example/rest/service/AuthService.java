package com.example.rest.service;

import com.example.rest.models.entities.User;

public interface AuthService {
    User login(String username, String password);
}
