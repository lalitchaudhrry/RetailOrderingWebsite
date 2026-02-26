package com.springbites.controller;

import com.springbites.dto.OrderResponse;
import com.springbites.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place")
    public OrderResponse placeOrder(@RequestParam Long userId) {
        return orderService.placeOrder(userId);
    }

    @GetMapping
    public List<OrderResponse> orderHistory(@RequestParam Long userId) {
        return orderService.getOrderHistory(userId);
    }
}