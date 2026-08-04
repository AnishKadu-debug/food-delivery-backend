package com.fooddelivery.food_delivery_backend.payment.service.impl;

import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.notification.enums.NotificationType;
import com.fooddelivery.food_delivery_backend.notification.service.NotificationService;
import com.fooddelivery.food_delivery_backend.order.entity.Order;
import com.fooddelivery.food_delivery_backend.order.repository.OrderRepository;
import com.fooddelivery.food_delivery_backend.payment.dto.CreatePaymentRequest;
import com.fooddelivery.food_delivery_backend.payment.dto.PaymentResponse;
import com.fooddelivery.food_delivery_backend.payment.entity.Payment;
import com.fooddelivery.food_delivery_backend.payment.enums.PaymentMethod;
import com.fooddelivery.food_delivery_backend.payment.enums.PaymentStatus;
import com.fooddelivery.food_delivery_backend.payment.mapper.PaymentMapper;
import com.fooddelivery.food_delivery_backend.payment.repository.PaymentRepository;
import com.fooddelivery.food_delivery_backend.payment.service.PaymentService;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final CurrentUserService currentUserService;
    private final NotificationService notificationService;

    @Override
    public PaymentResponse createPayment(CreatePaymentRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        if (!order.getCustomer().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "You can only pay for your own orders");
        }

        paymentRepository.findByOrder(order)
                .ifPresent(payment -> {
                    throw new IllegalArgumentException(
                            "Payment already exists for this order");
                });

        Payment payment = Payment.builder()
                .order(order)
                .customer(currentUser)
                .amount(order.getTotalAmount())
                .paymentMethod(request.getPaymentMethod())
                .build();

        if (request.getPaymentMethod() == PaymentMethod.COD) {

            payment.setPaymentStatus(PaymentStatus.PENDING);

        } else {

            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setPaidAt(LocalDateTime.now());
            payment.setTransactionId(UUID.randomUUID().toString());

        }

        Payment savedPayment = paymentRepository.save(payment);

        if (savedPayment.getPaymentMethod() == PaymentMethod.COD) {

            notificationService.createNotification(
                    currentUser,
                    "Cash on Delivery Selected",
                    "Your Cash on Delivery payment has been registered for Order #"
                            + order.getId(),
                    NotificationType.GENERAL
            );

        } else {

            notificationService.createNotification(
                    currentUser,
                    "Payment Successful",
                    "Your payment for Order #" + order.getId()
                            + " was completed successfully.",
                    NotificationType.PAYMENT_SUCCESS
            );
        }

        return PaymentMapper.toResponse(savedPayment);
    }

    @Override
    public PaymentResponse getPaymentById(Long paymentId) {

        User currentUser = currentUserService.getCurrentUser();

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found"));

        if (!payment.getCustomer().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "You cannot access this payment");
        }

        return PaymentMapper.toResponse(payment);
    }

    @Override
    public List<PaymentResponse> getMyPayments() {

        User currentUser = currentUserService.getCurrentUser();

        return paymentRepository.findByCustomer(currentUser)
                .stream()
                .map(PaymentMapper::toResponse)
                .toList();
    }
}