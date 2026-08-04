package com.fooddelivery.food_delivery_backend.order.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.order.dto.PlaceOrderRequest;
import com.fooddelivery.food_delivery_backend.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(
        name = "Orders",
        description = "APIs for placing orders and managing the complete restaurant order lifecycle."
)
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Place a new order",
            description = "Creates a new order from the authenticated customer's shopping cart."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Order placed successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid order request"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            )
    })
    @PostMapping
    public ApiResponse<OrderResponse> placeOrder(
            @Valid @RequestBody PlaceOrderRequest request) {

        return ApiResponse.success(
                "Order placed successfully",
                orderService.placeOrder(request)
        );
    }

    @Operation(
            summary = "Get my orders",
            description = "Returns all orders placed by the authenticated customer."
    )
    @GetMapping
    public ApiResponse<List<OrderResponse>> getMyOrders() {

        return ApiResponse.success(
                "Orders fetched successfully",
                orderService.getMyOrders()
        );
    }

    @Operation(
            summary = "Get order by ID",
            description = "Returns complete information about a specific order."
    )
    @GetMapping("/{id}")
    public ApiResponse<OrderResponse> getOrderById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order fetched successfully",
                orderService.getOrderById(id)
        );
    }

    @Operation(
            summary = "Accept order",
            description = "Accepts a newly placed order. Restaurant owner only."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Order accepted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Order not found"
            )
    })
    @PutMapping("/{id}/accept")
    public ApiResponse<OrderResponse> acceptOrder(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order accepted successfully",
                orderService.acceptOrder(id)
        );
    }

    @Operation(
            summary = "Reject order",
            description = "Rejects a newly placed order. Restaurant owner only."
    )
    @PutMapping("/{id}/reject")
    public ApiResponse<OrderResponse> rejectOrder(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order rejected successfully",
                orderService.rejectOrder(id)
        );
    }

    @Operation(
            summary = "Start preparing order",
            description = "Moves an accepted order to the PREPARING state."
    )
    @PutMapping("/{id}/prepare")
    public ApiResponse<OrderResponse> startPreparing(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order moved to preparing",
                orderService.startPreparing(id)
        );
    }

    @Operation(
            summary = "Mark order ready for pickup",
            description = "Marks the order as ready for pickup by the delivery partner."
    )
    @PutMapping("/{id}/ready")
    public ApiResponse<OrderResponse> markReadyForPickup(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order is ready for pickup",
                orderService.markReadyForPickup(id)
        );
    }

    @Operation(
            summary = "Cancel order",
            description = "Cancels an order if cancellation is permitted in its current state."
    )
    @PutMapping("/{id}/cancel")
    public ApiResponse<OrderResponse> cancelOrder(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Order cancelled successfully",
                orderService.cancelOrder(id)
        );
    }
}