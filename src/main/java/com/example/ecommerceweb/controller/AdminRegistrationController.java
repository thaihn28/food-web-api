package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.dto.UserRegistrationDto;
import com.example.ecommerceweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin")
public class AdminRegistrationController {
        private UserService userService;

        public AdminRegistrationController(UserService userService) {
            this.userService = userService;
        }

        @ModelAttribute("user")
        public UserRegistrationDto registrationDto(){
            return new UserRegistrationDto();
        }

        @GetMapping(value = "/registrationAdmin")
        public String showRegistrationForm(){
            return "registrationAdmin";
        }

        @PostMapping(value = "/registrationAdmin")
        public String registerUserAccount(@ModelAttribute("user")
                                                  UserRegistrationDto registrationDto){
            userService.saveAdmin(registrationDto);
            return "redirect:/admin/registratonAdmin?success";
        }
}
