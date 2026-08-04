package com.fooddelivery.food_delivery_backend.payment.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.payment.dto.CreatePaymentRequest;
import com.fooddelivery.food_delivery_backend.payment.dto.PaymentResponse;
import com.fooddelivery.food_delivery_backend.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(
        name = "Payments",
        description = "APIs for processing and viewing customer payments."
)
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(
            summary = "Create payment",
            description = "Creates a payment for an order. Online payment methods are simulated while COD remains pending."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Payment created successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid payment request"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Authentication required"
            )
    })
    @PostMapping
    public ApiResponse<PaymentResponse> createPayment(
            @Valid @RequestBody CreatePaymentRequest request) {

        return ApiResponse.success(
                "Payment created successfully",
                paymentService.createPayment(request)
        );
    }

    @Operation(
            summary = "Get payment by ID",
            description = "Returns detailed information about a specific payment."
    )
    @GetMapping("/{id}")
    public ApiResponse<PaymentResponse> getPaymentById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Payment fetched successfully",
                paymentService.getPaymentById(id)
        );
    }

    @Operation(
            summary = "Get my payments",
            description = "Returns all payments made by the authenticated customer."
    )
    @GetMapping
    public ApiResponse<List<PaymentResponse>> getMyPayments() {

        return ApiResponse.success(
                "Payments fetched successfully",
                paymentService.getMyPayments()
        );
    }
}