package com.fooddelivery.food_delivery_backend.delivery.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.delivery.dto.AssignDeliveryRequest;
import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;
import com.fooddelivery.food_delivery_backend.delivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
@Tag(
        name = "Delivery",
        description = "APIs for assigning delivery partners and managing the complete delivery lifecycle."
)
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Operation(
            summary = "Assign delivery partner",
            description = "Assigns an available delivery partner to an order. Accessible only to the restaurant owner."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Delivery partner assigned successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Order or delivery partner not found"
            )
    })
    @PostMapping("/orders/{orderId}/assign")
    public ApiResponse<DeliveryResponse> assignDeliveryPartner(
            @PathVariable Long orderId,
            @Valid @RequestBody AssignDeliveryRequest request) {

        return ApiResponse.success(
                "Delivery partner assigned successfully",
                deliveryService.assignDeliveryPartner(orderId, request)
        );
    }

    @Operation(
            summary = "Get my deliveries",
            description = "Returns all deliveries assigned to the authenticated delivery partner."
    )
    @GetMapping("/me")
    public ApiResponse<List<DeliveryResponse>> getMyDeliveries() {

        return ApiResponse.success(
                "Deliveries fetched successfully",
                deliveryService.getMyDeliveries()
        );
    }

    @Operation(
            summary = "Get delivery by ID",
            description = "Returns detailed information about a specific delivery."
    )
    @GetMapping("/{id}")
    public ApiResponse<DeliveryResponse> getDelivery(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Delivery fetched successfully",
                deliveryService.getDelivery(id)
        );
    }

    @Operation(
            summary = "Accept delivery",
            description = "Accepts an assigned delivery. Accessible only to the assigned delivery partner."
    )
    @PutMapping("/{id}/accept")
    public ApiResponse<DeliveryResponse> acceptDelivery(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Delivery accepted successfully",
                deliveryService.acceptDelivery(id)
        );
    }

    @Operation(
            summary = "Arrive at restaurant",
            description = "Marks the delivery partner as arrived at the restaurant."
    )
    @PutMapping("/{id}/arrive")
    public ApiResponse<DeliveryResponse> arriveAtRestaurant(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Arrived at restaurant",
                deliveryService.arriveAtRestaurant(id)
        );
    }

    @Operation(
            summary = "Pick up order",
            description = "Marks the order as picked up from the restaurant."
    )
    @PutMapping("/{id}/pickup")
    public ApiResponse<DeliveryResponse> pickupOrder(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order picked up successfully",
                deliveryService.pickupOrder(id)
        );
    }

    @Operation(
            summary = "Complete delivery",
            description = "Marks the order as successfully delivered to the customer."
    )
    @PutMapping("/{id}/deliver")
    public ApiResponse<DeliveryResponse> completeDelivery(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order delivered successfully",
                deliveryService.completeDelivery(id)
        );
    }
}