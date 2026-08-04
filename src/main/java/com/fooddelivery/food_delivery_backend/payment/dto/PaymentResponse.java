package com.fooddelivery.food_delivery_backend.payment.dto;

import com.fooddelivery.food_delivery_backend.payment.enums.PaymentMethod;
import com.fooddelivery.food_delivery_backend.payment.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents a payment returned by the API.")
public class PaymentResponse {

    @Schema(
            description = "Unique identifier of the payment.",
            example = "15"
    )
    private Long id;

    @Schema(
            description = "Identifier of the associated order.",
            example = "101"
    )
    private Long orderId;

    @Schema(
            description = "Identifier of the customer who made the payment.",
            example = "7"
    )
    private Long customerId;

    @Schema(
            description = "Payment amount.",
            example = "897.00"
    )
    private BigDecimal amount;

    @Schema(
            description = "Payment method used.",
            example = "UPI"
    )
    private PaymentMethod paymentMethod;

    @Schema(
            description = "Current payment status.",
            example = "SUCCESS"
    )
    private PaymentStatus paymentStatus;

    @Schema(
            description = "Transaction identifier generated for online payments.",
            example = "TXN-20260805123456"
    )
    private String transactionId;

    @Schema(
            description = "Date and time when the payment was completed."
    )
    private LocalDateTime paidAt;
}