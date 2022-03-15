package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(int id);
    List<Product> findAllByNameContains(String name);
    Page<Product> findAllByNameContains(String name, Pageable pageable);
}
