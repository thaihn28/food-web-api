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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// 2 session: Category and Product

@Controller
@RequestMapping("/admin")
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
//    @Autowired
//    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/categories")
    public String viewAllCategory(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/categories/add")
    public String addCategory(Model model){
        model.addAttribute("category" , new Category());
        model.addAttribute("pageTitle" , "Add new category");
        return "categoriesAdd";
    }

    @PostMapping("/categories/add")
    public String saveCategory(@RequestParam(value = "id", required = false) Long id, @ModelAttribute("category") Category category, RedirectAttributes ra ){
        categoryService.addCategory(category);
        System.out.println(id);
        if(id != 0){
            ra.addFlashAttribute("msg", "Updated successfully");
        }else {
            ra.addFlashAttribute("msg", "Added successfully");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategoryById(@PathVariable(value = "id") int id, RedirectAttributes ra){
        categoryService.deleteCategoryById(id);
        ra.addFlashAttribute("msg", "Deleted successfully");
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategoryById(@PathVariable(value = "id") int id, Model model){
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

    @GetMapping("/categories/{id}")
    public String detailCategory(@PathVariable("id") int id, Model model){
        try {
            Category category = categoryService.getCategoryById(id);
            List<Product> productList = productService.getAllProduct();
            model.addAttribute("category", category);
            model.addAttribute("products", productList);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return "redirect:/admin/categories";
        }
        return "categoriesDetail";
    }

    // Product sessions

    @GetMapping("/products")
    public String viewAllProduct(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model, RedirectAttributes ra){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        ra.addFlashAttribute("pageTitle", "Add a new product");
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String saveProduct(@RequestParam(value = "productId",required = false) Long id,
            @ModelAttribute("productDTO") ProductDTO productDTO, Model model,
                              @RequestParam("productImage")MultipartFile file,
                              @RequestParam("imgName") String imgName,
                              RedirectAttributes ra
                              ) throws IOException, UserNotFoundException {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()));
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        String imageUUID;

        if(id == null){
            ra.addFlashAttribute("msg", "Added successfully");
        }else {
            ra.addFlashAttribute("msg", "Updated successfully");
        }

        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else{
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "redirect:/admin/products";
    }

    @GetMapping("product/delete/{id}")
    public String deleteProductByID(@PathVariable(value = "id") Long id, RedirectAttributes ra){
        productService.deleteProductById(id);
        ra.addFlashAttribute("msg", "Deleted successfully");
        return "redirect:/admin/products";
    }

    @GetMapping("product/update/{id}")
    public String updateProductByID(@PathVariable(value = "id") Long id, Model model){
        try {
            Product product = productService.getProductById(id);

            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setName(product.getName());
            productDTO.setCategoryId(product.getCategory().getId());
            productDTO.setPrice(product.getPrice());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setDescription(product.getDescription());
            productDTO.setImageName(product.getImageName());

            model.addAttribute("categories", categoryService.getAllCategory());
            model.addAttribute("productDTO", productDTO);
            model.addAttribute("pageTitle" , "Update category (Id: " + id + ")" );
            return "productsAdd";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return "redirect:/admin/products";
        }
    }


}
