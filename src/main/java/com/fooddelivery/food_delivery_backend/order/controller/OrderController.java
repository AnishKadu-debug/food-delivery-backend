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

    @PutMapping("/{id}/accept")
    public ApiResponse<OrderResponse> acceptOrder(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order accepted successfully",
                orderService.acceptOrder(id)
        );
    }

    @PutMapping("/{id}/reject")
    public ApiResponse<OrderResponse> rejectOrder(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order rejected successfully",
                orderService.rejectOrder(id)
        );
    }

    @PutMapping("/{id}/prepare")
    public ApiResponse<OrderResponse> startPreparing(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order moved to preparing",
                orderService.startPreparing(id)
        );
    }

    @PutMapping("/{id}/ready")
    public ApiResponse<OrderResponse> markReadyForPickup(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order is ready for pickup",
                orderService.markReadyForPickup(id)
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