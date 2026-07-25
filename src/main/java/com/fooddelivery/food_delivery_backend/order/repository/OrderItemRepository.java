package com.fooddelivery.food_delivery_backend.order.repository;

import com.fooddelivery.food_delivery_backend.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}