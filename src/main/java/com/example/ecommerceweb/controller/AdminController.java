package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.service.CategoryService;
import com.example.ecommerceweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


// 2 session: Category and Product

@Controller
public class AdminController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "index";
    }
}
