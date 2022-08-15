package com.example.elearningsystem.xcontroller;

import com.example.elearningsystem.model.Role;
import com.example.elearningsystem.model.exception.InvalidArgumentsException;
import com.example.elearningsystem.model.exception.PasswordsDoNotMatchException;
import com.example.elearningsystem.service.AuthService;
import com.example.elearningsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname) {
        try{
            this.userService.register(username, password, repeatedPassword, name, surname, email);
            return "redirect:/home";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/home?error=" + exception.getMessage();
        }
    }
}

