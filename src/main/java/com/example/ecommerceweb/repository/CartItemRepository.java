package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
