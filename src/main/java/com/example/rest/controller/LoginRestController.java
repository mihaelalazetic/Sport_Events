package com.example.rest.controller;

import com.example.rest.models.entities.User;
import com.example.rest.models.enums.Role;
import com.example.rest.service.AuthService;
import com.example.rest.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest")
public class LoginRestController {

    private final AuthService authService;
    private final UserService userService;


    public LoginRestController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }


    @PostMapping("/login")
    public User login(HttpServletRequest request, @RequestParam String username, @RequestParam String password)  {
        User user = null;
//        try{
            user = this.authService.login(username,password);
            request.getSession().setAttribute("user", user);
            return user;
//        }
//        catch (RuntimeException exception) { // InvalidUserCredentialsException exception
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", exception.getMessage());
//            return "login";
//        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "Successfully logged out";
    }


    @PostMapping("/register")
    public User register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role) {
//        try{
            return this.userService.register(username, password, repeatedPassword, name, surname, role);
//        } catch (RuntimeException exception) { // InvalidArgumentsException | PasswordsDoNotMatchException exception
//            return "redirect:/register?error=" + exception.getMessage();
//        }
    }

}
