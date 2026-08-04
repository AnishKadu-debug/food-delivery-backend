package com.fooddelivery.food_delivery_backend.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents an item included in an order.")
public class OrderItemResponse {

    @Schema(
            description = "Unique identifier of the order item.",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Identifier of the menu item.",
            example = "15"
    )
    private Long menuItemId;

    @Schema(
            description = "Name of the ordered menu item.",
            example = "Paneer Butter Masala"
    )
    private String menuItemName;

    @Schema(
            description = "Quantity ordered.",
            example = "2"
    )
    private Integer quantity;

    @Schema(
            description = "Price of one unit of the menu item.",
            example = "299.00"
    )
    private BigDecimal price;

    @Schema(
            description = "Total price for this order item.",
            example = "598.00"
    )
    private BigDecimal totalPrice;
}