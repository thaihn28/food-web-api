package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.model.Product;
import com.example.ecommerceweb.service.ProductService;
import com.example.ecommerceweb.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        List<Product> productList = productService.getAllProduct();
        return productList;
    }

    @GetMapping("/productById")
    public Product productById(@RequestParam("id") Long id){
        try {
            Product product = productService.getProductById(id);
            return product;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    // ProductByCategory?id=
    @GetMapping("/productByCategory")
    public List<Product> getProductByCategory(@RequestParam("id") int id){
        List<Product> productsByCategoryId = productService.getAllProductsByCategoryId(id);
        return productsByCategoryId;
    }
}
