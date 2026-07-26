package com.fooddelivery.food_delivery_backend.payment.service;

import com.fooddelivery.food_delivery_backend.payment.dto.CreatePaymentRequest;
import com.fooddelivery.food_delivery_backend.payment.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {

    PaymentResponse createPayment(CreatePaymentRequest request);

    PaymentResponse getPaymentById(Long paymentId);

    List<PaymentResponse> getMyPayments();

}