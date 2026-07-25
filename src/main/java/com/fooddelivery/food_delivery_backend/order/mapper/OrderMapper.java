package com.fooddelivery.food_delivery_backend.order.mapper;

import com.fooddelivery.food_delivery_backend.order.dto.OrderItemResponse;
import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.order.entity.Order;
import com.fooddelivery.food_delivery_backend.order.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderMapper {

    private OrderMapper() {
    }

    public static OrderItemResponse toOrderItemResponse(OrderItem item) {

        return OrderItemResponse.builder()
                .id(item.getId())
                .menuItemId(item.getMenuItem().getId())
                .menuItemName(item.getMenuItem().getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .totalPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .build();
    }

    public static OrderResponse toResponse(Order order) {

        List<OrderItemResponse> items = order.getOrderItems()
                .stream()
                .map(OrderMapper::toOrderItemResponse)
                .toList();

        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .restaurantId(order.getRestaurant().getId())
                .addressId(order.getDeliveryAddress().getId())
                .status(order.getStatus())
                .items(items)
                .totalAmount(order.getTotalAmount())
                .build();
    }
}