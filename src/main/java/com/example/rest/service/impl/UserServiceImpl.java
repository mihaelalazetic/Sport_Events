package com.example.rest.service.impl;

import com.example.rest.models.entities.User;
import com.example.rest.models.enums.Role;
import com.example.rest.repositroy.SportEventRepository;
import com.example.rest.repositroy.UserRepository;
import com.example.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    private  SportEventRepository sportEventRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
        return userRepository.findByUsername(s);
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
//            throw new InvalidUsernameOrPasswordException();
            throw new RuntimeException();
        if (!password.equals(repeatPassword))
//            throw new PasswordsDoNotMatchException();
            throw new RuntimeException();
        if(this.userRepository.findByUsername(username) != null)
//            if(this.userRepository.findByUsername(username).isPresent())
//            throw new UsernameAlreadyExistsException(username);
            throw new RuntimeException();
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }

}
