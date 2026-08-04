package com.fooddelivery.food_delivery_backend.order.dto;

import com.fooddelivery.food_delivery_backend.order.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents an order returned by the API.")
public class OrderResponse {

    @Schema(
            description = "Unique identifier of the order.",
            example = "101"
    )
    private Long id;

    @Schema(
            description = "Identifier of the customer who placed the order.",
            example = "7"
    )
    private Long customerId;

    @Schema(
            description = "Identifier of the restaurant receiving the order.",
            example = "2"
    )
    private Long restaurantId;

    @Schema(
            description = "Identifier of the selected delivery address.",
            example = "5"
    )
    private Long addressId;

    @Schema(
            description = "Current status of the order.",
            example = "PREPARING"
    )
    private OrderStatus status;

    @Schema(
            description = "Items included in the order."
    )
    private List<OrderItemResponse> items;

    @Schema(
            description = "Total amount payable for the order.",
            example = "897.00"
    )
    private BigDecimal totalAmount;
}