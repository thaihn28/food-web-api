package com.example.ecommerceweb.repository;

import com.example.ecommerceweb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
