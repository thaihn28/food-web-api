package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.dto.ProductDTO;
import com.example.ecommerceweb.model.Category;
import com.example.ecommerceweb.model.Product;
import com.example.ecommerceweb.service.CategoryService;
import com.example.ecommerceweb.service.ProductService;
import com.example.ecommerceweb.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

// 2 session: Category and Product

@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategory(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model model){
        Category category = new Category();
        model.addAttribute("category" , category);
        model.addAttribute("pageTitle" , "Add new category");
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String saveCategory(@RequestParam(value = "id", required = false) Long id, @ModelAttribute("category") Category category, RedirectAttributes ra ){
        categoryService.addCategory(category);

        if(id == null){
            ra.addFlashAttribute("msg", "Added successfully");
        }else {
            ra.addFlashAttribute("msg", "Updated successfully");
        }

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategoryById(@PathVariable(value = "id") Long id, RedirectAttributes ra){
        categoryService.deleteCategoryById(id);
        ra.addFlashAttribute("msg", "Deleted successfully");
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategoryById(@PathVariable(value = "id") Long id, Model model){
        try {
            Category category = categoryService.getCategoryById(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle" , "Update category (Id: " + id + ")" );
            return "categoriesAdd";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return "redirect:/admin/categories";
        }
    }

    // Product sessions

    @GetMapping("/admin/products")
    public String viewAllProduct(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model){
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String saveProduct(@ModelAttribute("productDTO") Product product, Model model){
        productService.addProduct(product);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "redirect:/admin/products";
    }

}
