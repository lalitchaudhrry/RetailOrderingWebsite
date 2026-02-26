package com.springbites.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private double price;
    private int stock;
    private String categoryName;
    private String brandName;
}