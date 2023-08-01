package com.example.rest.service;


import com.example.rest.models.entities.User;
import com.example.rest.models.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

}

