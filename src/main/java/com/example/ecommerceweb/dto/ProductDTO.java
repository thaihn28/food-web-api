package com.example.ecommerceweb.dto;

import lombok.Data;


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
