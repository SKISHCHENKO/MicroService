package com.example.orders.service;

import com.example.orders.model.Order;
import com.example.orders.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
        orders.add(new Order("1", "1", 1500.0, "RUB", 
            List.of(new OrderItem("Товар 1", 2, 750.0))));
        orders.add(new Order("2", "2", 2000.0, "RUB",
            List.of(new OrderItem("Товар 2", 1, 2000.0))));
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orders.stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public Optional<Order> getOrderById(String orderId) {
        try {
            int index = Integer.parseInt(orderId) - 1;
            if (index < 0 || index >= orders.size()) {
                return Optional.empty();
            }
            return Optional.of(orders.get(index));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}