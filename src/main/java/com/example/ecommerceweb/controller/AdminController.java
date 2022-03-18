package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.service.CategoryService;
import com.example.ecommerceweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


// 2 session: Category and Product

@Controller
public class AdminController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

//    @GetMapping("/admin")
//    public String admin(Model model){
//        model.addAttribute("products", productService.getAllProduct());
//        model.addAttribute("categories", categoryService.getAllCategory());
//        AppUser user = new AppUser();
//        model.addAttribute("user",user);
//        return "index";
//    }
}
