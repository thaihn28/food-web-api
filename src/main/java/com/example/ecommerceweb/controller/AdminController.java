package com.example.ecommerceweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


// 2 session: Category and Product

@Controller

public class AdminController {

    @GetMapping("/")
    public String homePage(){
        return "homePage";
    }

    @GetMapping("/admin")
    public String adminHome(){
        return "/admin/adminHome";
    }

}
