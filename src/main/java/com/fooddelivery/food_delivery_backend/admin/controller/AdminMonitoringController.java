package com.fooddelivery.food_delivery_backend.admin.controller;

import com.fooddelivery.food_delivery_backend.admin.service.AdminMonitoringService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;
import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.payment.dto.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/monitoring")
@RequiredArgsConstructor
@Tag(
        name = "Admin Monitoring",
        description = "Administrative APIs for monitoring orders, payments, and deliveries."
)
public class AdminMonitoringController {

    private final AdminMonitoringService adminMonitoringService;

    @Operation(
            summary = "Get all orders",
            description = "Returns all orders in the system."
    )
    @GetMapping("/orders")
    public ApiResponse<List<OrderResponse>> getAllOrders() {

        return ApiResponse.success(
                "Orders fetched successfully",
                adminMonitoringService.getAllOrders()
        );
    }

    @Operation(
            summary = "Get order by ID",
            description = "Returns detailed information about a specific order."
    )
    @GetMapping("/orders/{id}")
    public ApiResponse<OrderResponse> getOrderById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order fetched successfully",
                adminMonitoringService.getOrderById(id)
        );
    }

    @Operation(
            summary = "Get all payments",
            description = "Returns all payments in the system."
    )
    @GetMapping("/payments")
    public ApiResponse<List<PaymentResponse>> getAllPayments() {

        return ApiResponse.success(
                "Payments fetched successfully",
                adminMonitoringService.getAllPayments()
        );
    }

    @Operation(
            summary = "Get payment by ID",
            description = "Returns detailed information about a specific payment."
    )
    @GetMapping("/payments/{id}")
    public ApiResponse<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Payment fetched successfully",
                adminMonitoringService.getPaymentById(id)
        );
    }

    @Operation(
            summary = "Get all deliveries",
            description = "Returns all deliveries in the system."
    )
    @GetMapping("/deliveries")
    public ApiResponse<List<DeliveryResponse>> getAllDeliveries() {

        return ApiResponse.success(
                "Deliveries fetched successfully",
                adminMonitoringService.getAllDeliveries()
        );
    }

    @Operation(
            summary = "Get delivery by ID",
            description = "Returns detailed information about a specific delivery."
    )
    @GetMapping("/deliveries/{id}")
    public ApiResponse<DeliveryResponse> getDeliveryById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Delivery fetched successfully",
                adminMonitoringService.getDeliveryById(id)
        );
    }
}