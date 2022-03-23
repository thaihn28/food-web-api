package com.example.ecommerceweb.api.reservation;

import com.example.ecommerceweb.model.Category;
import com.example.ecommerceweb.model.Product;
import com.example.ecommerceweb.service.CategoryService;
import com.example.ecommerceweb.service.ProductService;
import com.example.ecommerceweb.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProduct();
    }

    // productById?id=
    @GetMapping("/productById/{id}")
    public Product productById(@PathVariable("id") Long id){
        try {
            return productService.getProductById(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    // productByCategory?id=
    @GetMapping("/productByCategory/{id}")
    public List<Product> getProductByCategory(@PathVariable("id") int id){
        return productService.getAllProductsByCategoryId(id);
    }

    @GetMapping("/searchCategory/{name}")
    public List<Category> getAllCategoryByName(@PathVariable("name") String name){
        return categoryService.findAllCategoryByName(name);
    }
    @GetMapping("/searchProduct/{name}")
    public List<Product> getAllProductByName(@PathVariable("name") String name){
        return productService.getAllProductsByName(name);
    }
    @GetMapping("/sortProduct/asc")
    public List<Product> sortProdcutAsc(){
        return productService.sortProductByPriceAsc();
    }
    @GetMapping("/sortProduct/desc")
    public List<Product> sortProductDesc(){
        return productService.sortProductByPriceDesc();
    }

}
