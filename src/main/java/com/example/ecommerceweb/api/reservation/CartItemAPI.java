package com.example.ecommerceweb.api.reservation;

import com.example.ecommerceweb.dto.CartItemDTO;
import com.example.ecommerceweb.model.CartItem;
import com.example.ecommerceweb.service.CartItemService;
import com.example.ecommerceweb.service.ProductService;
import com.example.ecommerceweb.service.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class CartItemAPI {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    ProductService productService;

    @PostMapping("/carts/add")
    public CartItemDTO addCartItem(@RequestBody CartItemDTO cartItemDTO) throws ParseException, UserNotFoundException {
            CartItem cartItem = new CartItem();

            cartItem.setId(cartItemDTO.getId());
            cartItem.setProduct(productService.getProductById(cartItemDTO.getProductId()));
            cartItem.setUsername(cartItemDTO.getUsername());
            cartItem.setPhoneNo(cartItemDTO.getPhoneNo());
            cartItem.setAddress(cartItemDTO.getAddress());
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItem.setTotal(cartItemDTO.getTotal());
            cartItem.setDate(cartItemDTO.getDate());
            cartItem.setApprove(false);

            cartItemService.saveCartItem(cartItem);

        return cartItemDTO;
    }

}
