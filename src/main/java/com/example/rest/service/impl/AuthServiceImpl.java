package com.example.rest.service.impl;

import com.example.rest.models.entities.User;
import com.example.rest.repositroy.UserRepository;
import com.example.rest.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
//            throw new InvalidArgumentsException();
            throw new RuntimeException();
        }
        return userRepository.findByUsernameAndPassword(username,
//                password).orElseThrow(InvalidUserCredentialsException::new);
                password).orElseThrow(RuntimeException::new);
    }

}

