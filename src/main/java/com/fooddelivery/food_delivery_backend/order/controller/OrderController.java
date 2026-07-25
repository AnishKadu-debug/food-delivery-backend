package com.fooddelivery.food_delivery_backend.order.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.order.dto.PlaceOrderRequest;
import com.fooddelivery.food_delivery_backend.order.dto.UpdateOrderStatusRequest;
import com.fooddelivery.food_delivery_backend.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> placeOrder(
            @Valid @RequestBody PlaceOrderRequest request) {

        return ApiResponse.success(
                "Order placed successfully",
                orderService.placeOrder(request)
        );
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getMyOrders() {

        return ApiResponse.success(
                "Orders fetched successfully",
                orderService.getMyOrders()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getOrderById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order fetched successfully",
                orderService.getOrderById(id)
        );
    }

    @PutMapping("/{id}/status")
    public ApiResponse<OrderResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {

        return ApiResponse.success(
                "Order status updated successfully",
                orderService.updateOrderStatus(id, request)
        );
    }

    @PutMapping("/{id}/cancel")
    public ApiResponse<OrderResponse> cancelOrder(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order cancelled successfully",
                orderService.cancelOrder(id)
        );
    }
}