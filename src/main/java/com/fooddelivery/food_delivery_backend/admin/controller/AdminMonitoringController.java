package com.fooddelivery.food_delivery_backend.admin.controller;

import com.fooddelivery.food_delivery_backend.admin.service.AdminMonitoringService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;
import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.payment.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/monitoring")
@RequiredArgsConstructor
public class AdminMonitoringController {

    private final AdminMonitoringService adminMonitoringService;

    @GetMapping("/orders")
    public ApiResponse<List<OrderResponse>> getAllOrders() {

        return ApiResponse.success(
                "Orders fetched successfully",
                adminMonitoringService.getAllOrders()
        );
    }

    @GetMapping("/orders/{id}")
    public ApiResponse<OrderResponse> getOrderById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order fetched successfully",
                adminMonitoringService.getOrderById(id)
        );
    }

    @GetMapping("/payments")
    public ApiResponse<List<PaymentResponse>> getAllPayments() {

        return ApiResponse.success(
                "Payments fetched successfully",
                adminMonitoringService.getAllPayments()
        );
    }

    @GetMapping("/payments/{id}")
    public ApiResponse<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Payment fetched successfully",
                adminMonitoringService.getPaymentById(id)
        );
    }

    @GetMapping("/deliveries")
    public ApiResponse<List<DeliveryResponse>> getAllDeliveries() {

        return ApiResponse.success(
                "Deliveries fetched successfully",
                adminMonitoringService.getAllDeliveries()
        );
    }

    @GetMapping("/deliveries/{id}")
    public ApiResponse<DeliveryResponse> getDeliveryById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Delivery fetched successfully",
                adminMonitoringService.getDeliveryById(id)
        );
    }
}