package com.springbites.controller;

import com.springbites.dto.AddToCartRequest;
import com.springbites.dto.CartResponse;
import com.springbites.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public CartResponse addToCart(@RequestBody AddToCartRequest request) {
        return cartService.addToCart(request);
    }

    @GetMapping
    public CartResponse getCart(@RequestParam Long userId) {
        return cartService.getCart(userId);
    }

    @DeleteMapping("/clear")
    public void clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
    }
}