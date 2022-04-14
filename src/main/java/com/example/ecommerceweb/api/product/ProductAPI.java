package com.example.ecommerceweb.api.product;

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

@RequestMapping("/api")
@RestController
public class ProductAPI {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/products")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
            @RequestParam(value = "orderBy", required = false, defaultValue = "name") String orderBy,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        try {
            Page<Product> pageRes = productService.getProductsWithPaginationAndSorting(page - 1, pageSize, orderBy, order);
            Map<String, Object> response = new HashMap<>();
            response.put("data", pageRes.getContent());
            response.put("currentPage", pageRes.getNumber());
            response.put("totalItems", pageRes.getTotalElements());
            response.put("totalPages", pageRes.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/product/{id}")
    public Product productById(@PathVariable("id") Long id) {
        try {
            return productService.getProductById(id);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/category/{id}")
    public List<Product> productNameById(@PathVariable("id") int id) {
        return productService.getAllProductsByCategoryId(id);
    }
}
