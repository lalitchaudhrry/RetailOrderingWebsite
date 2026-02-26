package com.springbites.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Price is required")
    private Double price;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @NotNull(message = "Category id is required")
    private Long categoryId;

    @NotNull(message = "Brand id is required")
    private Long brandId;
}