package com.fooddelivery.food_delivery_backend.admin.service;

import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;
import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.payment.dto.PaymentResponse;

import java.util.List;

public interface AdminMonitoringService {

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long orderId);

    List<PaymentResponse> getAllPayments();

    PaymentResponse getPaymentById(Long paymentId);

    List<DeliveryResponse> getAllDeliveries();

    DeliveryResponse getDeliveryById(Long deliveryId);
}