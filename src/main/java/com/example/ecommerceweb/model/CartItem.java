package com.example.ecommerceweb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    @Column(length = 10)
    private String phoneNo;
    @NotNull
    private String address;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NotNull
    private int quantity;
    @NotNull
    private double total;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date date;
    @NotNull
    private boolean isApprove;

}
