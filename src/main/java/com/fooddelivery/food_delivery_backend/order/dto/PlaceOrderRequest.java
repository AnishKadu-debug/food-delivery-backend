package com.fooddelivery.food_delivery_backend.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for placing a new order.")
public class PlaceOrderRequest {

    @Schema(
            description = "Identifier of the delivery address to be used for the order.",
            example = "3"
    )
    @NotNull(message = "Delivery address is required")
    private Long addressId;
}