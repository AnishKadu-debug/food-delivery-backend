package com.fooddelivery.food_delivery_backend.payment.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.payment.dto.CreatePaymentRequest;
import com.fooddelivery.food_delivery_backend.payment.dto.PaymentResponse;
import com.fooddelivery.food_delivery_backend.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ApiResponse<PaymentResponse> createPayment(
            @Valid @RequestBody CreatePaymentRequest request) {

        return ApiResponse.success(
                "Payment created successfully",
                paymentService.createPayment(request)
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Payment fetched successfully",
                paymentService.getPaymentById(id)
        );
    }

    @GetMapping
    public ApiResponse<List<PaymentResponse>> getMyPayments() {

        return ApiResponse.success(
                "Payments fetched successfully",
                paymentService.getMyPayments()
        );
    }
}