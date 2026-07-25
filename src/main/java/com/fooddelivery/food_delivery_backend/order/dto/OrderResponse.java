package com.fooddelivery.food_delivery_backend.order.dto;

import com.fooddelivery.food_delivery_backend.order.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;

    private Long customerId;

    private Long restaurantId;

    private Long addressId;

    private OrderStatus status;

    private List<OrderItemResponse> items;

    private BigDecimal totalAmount;
}