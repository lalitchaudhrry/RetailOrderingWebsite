package com.springbites.service;

import com.springbites.dto.OrderItemResponse;
import com.springbites.dto.OrderResponse;
import com.springbites.entity.Cart;
import com.springbites.entity.CartItem;
import com.springbites.entity.Order;
import com.springbites.entity.OrderItem;
import com.springbites.entity.Product;
import com.springbites.exception.CartEmptyException;
import com.springbites.exception.InsufficientStockException;
import com.springbites.exception.ResourceNotFoundException;
import com.springbites.repository.CartRepository;
import com.springbites.repository.OrderRepository;
import com.springbites.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponse placeOrder(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: " + userId));

        if (cart.getItems().isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus("CONFIRMED");

        double total = 0;

        for (CartItem ci : cart.getItems()) {
            Product product = ci.getProduct();

            // ✅ Stock check using Product.stock
            if (product.getStock() < ci.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for " + product.getName());
            }

            // ✅ Reduce stock
            product.setStock(product.getStock() - ci.getQuantity());
            productRepository.save(product);

            // ✅ Create order item
            OrderItem oi = new OrderItem();
            oi.setProduct(product);
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(product.getPrice());

            order.getItems().add(oi);

            // ✅ Calculate total
            total += product.getPrice() * ci.getQuantity();
        }

        order.setTotalAmount(total);
        Order saved = orderRepository.save(order);

        // ✅ Clear cart after successful order
        cart.getItems().clear();
        cartRepository.save(cart);

        return mapToResponse(saved);
    }

    public List<OrderResponse> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private OrderResponse mapToResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                order.getItems().stream()
                        .map(i -> new OrderItemResponse(
                                i.getProduct().getId(),
                                i.getProduct().getName(),
                                i.getPrice(),
                                i.getQuantity()
                        ))
                        .collect(Collectors.toList())
        );
    }
}