package com.springbites.service;

import com.springbites.dto.AddToCartRequest;
import com.springbites.dto.CartItemResponse;
import com.springbites.dto.CartResponse;
import com.springbites.entity.Cart;
import com.springbites.entity.CartItem;
import com.springbites.entity.Product;
import com.springbites.exception.ResourceNotFoundException;
import com.springbites.repository.CartRepository;
import com.springbites.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartResponse addToCart(AddToCartRequest request) {

        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUserId(request.getUserId());
                    return c;
                });

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        cart.getItems().add(item);
        Cart saved = cartRepository.save(cart);

        return mapToResponse(saved);
    }

    public CartResponse getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));
        return mapToResponse(cart);
    }

    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    private CartResponse mapToResponse(Cart cart) {
        return new CartResponse(
                cart.getId(),
                cart.getUser().getId(),
                cart.getItems().stream()
                        .map(i -> new CartItemResponse(
                                i.getId(),
                                i.getProduct().getId(),
                                i.getProduct().getName(),
                                i.getQuantity(),
                                i.getProduct().getPrice()
                        ))
                        .collect(Collectors.toList())
        );
    }
}