package com.fooddelivery.food_delivery_backend.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderRequest {

    @NotNull(message = "Delivery address is required")
    private Long addressId;

}