package com.fooddelivery.food_delivery_backend.order.service;

import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.order.dto.PlaceOrderRequest;
import com.fooddelivery.food_delivery_backend.order.dto.UpdateOrderStatusRequest;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(PlaceOrderRequest request);

    List<OrderResponse> getMyOrders();

    OrderResponse getOrderById(Long orderId);

    OrderResponse updateOrderStatus(Long orderId,
                                    UpdateOrderStatusRequest request);

    OrderResponse cancelOrder(Long orderId);

}