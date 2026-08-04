package com.fooddelivery.food_delivery_backend.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for assigning a delivery partner to an order.")
public class AssignDeliveryRequest {

    @Schema(
            description = "Identifier of the delivery partner.",
            example = "12"
    )
    @NotNull(message = "Delivery partner is required")
    private Long deliveryPartnerId;
}