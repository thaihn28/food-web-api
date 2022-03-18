package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.CartItem;
import com.example.ecommerceweb.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    public List<CartItem> getAllCart(){
        return cartItemRepository.findAll();
    }

    public void saveCartItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }
    public CartItem getCartItemById(Long id) throws UserNotFoundException {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(cartItem.isPresent()){
            return cartItem.get();
        }
        throw new UserNotFoundException("Can not found this cart item");
    }

    // Pagination
    public Page<CartItem> findPaginated(int pageNo){
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<CartItem> cartItems = cartItemRepository.findAll(pageable);
        return cartItems;
    }

}
