package com.example.ecommerceweb.controller;

import com.example.ecommerceweb.model.CartItem;
import com.example.ecommerceweb.service.CartItemService;
import com.example.ecommerceweb.service.ProductService;
import com.example.ecommerceweb.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    ProductService productService;

    @GetMapping("/carts")
    public String viewAllCartItem(Model model){
//        List<CartItem> cartItemList = cartItemService.getAllCart();
//        model.addAttribute("cartItemList", cartItemList);
//        model.addAttribute("products", productService.getAllProduct());
        return getOnePage(model, 1);
    }

    // Pagination
    @GetMapping("/carts/page/{pageNo}")
    public String getOnePage(Model model, @PathVariable(value = "pageNo") int pageNo){
        Page<CartItem> page = cartItemService.findPaginated(pageNo);
        List<CartItem> cartItemList = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("carts", cartItemList);

        return "/cart/carts";
    }

    @GetMapping("/carts/detail/{id}")
    public String detailCustomer(Model model, @PathVariable("id") Long id) throws UserNotFoundException {
        model.addAttribute("cardDetail", cartItemService.getCartItemById(id));
        return "/cart/carts";
    }

    @GetMapping("/carts/approve/{id}")
    public String updateCartItemStatus(Model model, @PathVariable("id") Long id, CartItem cartItem){
        try {
             cartItem = cartItemService.getCartItemById(id);
            if(!cartItem.isApprove()){
                cartItem.setApprove(true);
            }
            else{
                cartItem.setApprove(false);
            }
            System.out.println(cartItem.isApprove());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        cartItemService.saveCartItem(cartItem);
        return "redirect:/admin/carts";
    }
}
