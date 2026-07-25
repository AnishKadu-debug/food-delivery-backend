package com.fooddelivery.food_delivery_backend.order.util;

import com.fooddelivery.food_delivery_backend.order.enums.OrderStatus;

import java.util.Map;
import java.util.Set;

public final class OrderStatusValidator {

    private OrderStatusValidator() {
    }

    private static final Map<OrderStatus, Set<OrderStatus>> VALID_TRANSITIONS = Map.of(
            OrderStatus.PLACED, Set.of(OrderStatus.ACCEPTED, OrderStatus.REJECTED, OrderStatus.CANCELLED),

            OrderStatus.ACCEPTED, Set.of(OrderStatus.PREPARING, OrderStatus.CANCELLED),

            OrderStatus.PREPARING, Set.of(OrderStatus.READY_FOR_PICKUP),

            OrderStatus.READY_FOR_PICKUP, Set.of(OrderStatus.OUT_FOR_DELIVERY),

            OrderStatus.OUT_FOR_DELIVERY, Set.of(OrderStatus.DELIVERED)
    );

    public static boolean isValidTransition(OrderStatus currentStatus,
                                            OrderStatus newStatus) {

        return VALID_TRANSITIONS
                .getOrDefault(currentStatus, Set.of())
                .contains(newStatus);
    }

}