package com.springbites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemResponse {

    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
}