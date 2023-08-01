package com.example.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.context.annotation.Configuration
public class Configuration {
//    @Bean
//    public SportEventService getSportEventService() {
//        return new SportEventServiceImpl();
//    }
    @Bean
    PasswordEncoder getPassEncoder() {
        return new BCryptPasswordEncoder();
    }
}
