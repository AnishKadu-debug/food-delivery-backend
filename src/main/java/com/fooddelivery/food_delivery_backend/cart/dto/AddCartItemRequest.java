package com.fooddelivery.food_delivery_backend.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for adding an item to the shopping cart.")
public class AddCartItemRequest {

    @Schema(
            description = "Unique identifier of the menu item.",
            example = "15"
    )
    @NotNull(message = "Menu item id is required")
    private Long menuItemId;

    @Schema(
            description = "Quantity of the menu item.",
            example = "2"
    )
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}