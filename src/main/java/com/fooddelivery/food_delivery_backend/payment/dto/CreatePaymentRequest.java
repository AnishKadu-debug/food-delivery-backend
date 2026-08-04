package com.fooddelivery.food_delivery_backend.payment.dto;

import com.fooddelivery.food_delivery_backend.payment.enums.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for creating a payment.")
public class CreatePaymentRequest {

    @Schema(
            description = "Identifier of the order to be paid.",
            example = "101"
    )
    @NotNull(message = "Order id is required")
    private Long orderId;

    @Schema(
            description = "Selected payment method.",
            example = "UPI"
    )
    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}