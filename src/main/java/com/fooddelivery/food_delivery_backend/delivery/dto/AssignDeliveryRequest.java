package com.fooddelivery.food_delivery_backend.delivery.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignDeliveryRequest {

    @NotNull(message = "Delivery partner is required")
    private Long deliveryPartnerId;

}