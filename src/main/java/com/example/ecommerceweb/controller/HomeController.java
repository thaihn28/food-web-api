package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.model.Category;
import com.example.ecommerceweb.model.Product;
import com.example.ecommerceweb.service.CategoryService;
import com.example.ecommerceweb.service.ProductService;
import com.example.ecommerceweb.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    // categories
    @GetMapping("/categories/{id}")
    public List<Product> getProductByCategory(@PathVariable("id") int id){
        return productService.getAllProductsByCategoryId(id);
    }

    @GetMapping("/categories/{name}")
    public List<Category> getAllCategoryByName(@PathVariable("name") String name){
        return categoryService.findAllCategoryByName(name);
    }

}
