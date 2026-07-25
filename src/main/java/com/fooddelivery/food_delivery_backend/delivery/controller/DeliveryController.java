package com.fooddelivery.food_delivery_backend.delivery.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.delivery.dto.AssignDeliveryRequest;
import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;
import com.fooddelivery.food_delivery_backend.delivery.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/orders/{orderId}/assign")
    public ApiResponse<DeliveryResponse> assignDeliveryPartner(
            @PathVariable Long orderId,
            @Valid @RequestBody AssignDeliveryRequest request) {

        return ApiResponse.success(
                "Delivery partner assigned successfully",
                deliveryService.assignDeliveryPartner(orderId, request)
        );
    }

    @GetMapping("/me")
    public ApiResponse<List<DeliveryResponse>> getMyDeliveries() {

        return ApiResponse.success(
                "Deliveries fetched successfully",
                deliveryService.getMyDeliveries()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<DeliveryResponse> getDelivery(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Delivery fetched successfully",
                deliveryService.getDelivery(id)
        );
    }

    @PutMapping("/{id}/accept")
    public ApiResponse<DeliveryResponse> acceptDelivery(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Delivery accepted successfully",
                deliveryService.acceptDelivery(id)
        );
    }

    @PutMapping("/{id}/arrive")
    public ApiResponse<DeliveryResponse> arriveAtRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Arrived at restaurant",
                deliveryService.arriveAtRestaurant(id)
        );
    }

    @PutMapping("/{id}/pickup")
    public ApiResponse<DeliveryResponse> pickupOrder(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order picked up successfully",
                deliveryService.pickupOrder(id)
        );
    }

    @PutMapping("/{id}/deliver")
    public ApiResponse<DeliveryResponse> completeDelivery(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order delivered successfully",
                deliveryService.completeDelivery(id)
        );
    }
}