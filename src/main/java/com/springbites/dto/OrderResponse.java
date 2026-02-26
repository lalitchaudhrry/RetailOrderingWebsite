package com.springbites.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String status;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;
}