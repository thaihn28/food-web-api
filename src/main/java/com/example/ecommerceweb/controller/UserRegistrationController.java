package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.service.UserService;
import com.example.ecommerceweb.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto registrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")
                              UserRegistrationDto registrationDto) {
        userService.saveUser(registrationDto);
        return "redirect:/registration?success";
    }

}
