package com.fooddelivery.food_delivery_backend.order.service;

import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.order.dto.PlaceOrderRequest;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(PlaceOrderRequest request);

    List<OrderResponse> getMyOrders();

    OrderResponse getOrderById(Long orderId);

    OrderResponse acceptOrder(Long orderId);

    OrderResponse rejectOrder(Long orderId);

    OrderResponse startPreparing(Long orderId);

    OrderResponse markReadyForPickup(Long orderId);

    OrderResponse cancelOrder(Long orderId);

}