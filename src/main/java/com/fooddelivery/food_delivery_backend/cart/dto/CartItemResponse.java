package com.fooddelivery.food_delivery_backend.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents an individual item in the shopping cart.")
public class CartItemResponse {

    @Schema(
            description = "Unique identifier of the cart item.",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Unique identifier of the menu item.",
            example = "15"
    )
    private Long menuItemId;

    @Schema(
            description = "Name of the menu item.",
            example = "Paneer Butter Masala"
    )
    private String menuItemName;

    @Schema(
            description = "Price of one unit of the menu item.",
            example = "299.00"
    )
    private BigDecimal price;

    @Schema(
            description = "Quantity added to the cart.",
            example = "2"
    )
    private Integer quantity;

    @Schema(
            description = "Total price for this cart item.",
            example = "598.00"
    )
    private BigDecimal totalPrice;
}