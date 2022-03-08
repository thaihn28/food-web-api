package com.example.ecommerceweb.dto;

import com.example.ecommerceweb.model.Category;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    private Long productId;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String imageName;
    private int categoryId;

}
