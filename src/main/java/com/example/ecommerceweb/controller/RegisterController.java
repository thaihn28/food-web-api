//package com.example.ecommerceweb.controller;
//
//import com.example.ecommerceweb.model.AppUser;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class RegisterController {
//
//    @GetMapping(value = "/login")
//    public String login(){
//        return "login";
//    }
//
//    @GetMapping(value = "/registration")
//    public String register(Model model){
//        AppUser user = new AppUser();
//        model.addAttribute("user",user);
//        return "register";
//    }
//
//    @PostMapping(value = "/add")
//    public String saveUser(@ModelAttribute AppUser user, Model model){
//        model.addAttribute("user",user);
//        return "register";
//    }
//
//}
//
//
//
//
