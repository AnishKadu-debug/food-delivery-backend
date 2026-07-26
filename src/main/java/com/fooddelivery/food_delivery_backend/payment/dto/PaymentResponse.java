package com.fooddelivery.food_delivery_backend.payment.dto;

import com.fooddelivery.food_delivery_backend.payment.enums.PaymentMethod;
import com.fooddelivery.food_delivery_backend.payment.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;

    private Long orderId;

    private Long customerId;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private String transactionId;

    private LocalDateTime paidAt;
}