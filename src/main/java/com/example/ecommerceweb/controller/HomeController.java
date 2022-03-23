package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.model.Category;
import com.example.ecommerceweb.model.Product;
import com.example.ecommerceweb.service.CategoryService;
import com.example.ecommerceweb.service.ProductService;
import com.example.ecommerceweb.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/products")
    public Page<Product> getAllProducts(
            @RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
            @RequestParam(value = "orderBy", required = false, defaultValue = "name") String orderBy,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return productService.getProductsWithPaginationAndSorting(page - 1, pageSize, orderBy, order);
    }

    // products
    @GetMapping("/products/{id}")
    public Product productById(@PathVariable("id") Long id){
        try {
            return productService.getProductById(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/products/{name}")
    public List<Product> getAllProductByName(@PathVariable("name") String name){
        return productService.getAllProductsByName(name);
    }

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
