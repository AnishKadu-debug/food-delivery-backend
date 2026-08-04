package com.fooddelivery.food_delivery_backend.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents the authenticated customer's shopping cart.")
public class CartResponse {

    @Schema(
            description = "Unique identifier of the cart.",
            example = "1"
    )
    private Long cartId;

    @Schema(
            description = "List of items currently in the shopping cart."
    )
    private List<CartItemResponse> items;

    @Schema(
            description = "Total number of items in the shopping cart.",
            example = "4"
    )
    private Integer totalItems;

    @Schema(
            description = "Subtotal of all items before taxes or delivery charges.",
            example = "1196.00"
    )
    private BigDecimal subtotal;
}