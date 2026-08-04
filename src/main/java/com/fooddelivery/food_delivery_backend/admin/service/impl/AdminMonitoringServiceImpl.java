package com.fooddelivery.food_delivery_backend.admin.service.impl;

import com.fooddelivery.food_delivery_backend.admin.service.AdminMonitoringService;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;
import com.fooddelivery.food_delivery_backend.delivery.entity.Delivery;
import com.fooddelivery.food_delivery_backend.delivery.mapper.DeliveryMapper;
import com.fooddelivery.food_delivery_backend.delivery.repository.DeliveryRepository;
import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.order.entity.Order;
import com.fooddelivery.food_delivery_backend.order.mapper.OrderMapper;
import com.fooddelivery.food_delivery_backend.order.repository.OrderRepository;
import com.fooddelivery.food_delivery_backend.payment.dto.PaymentResponse;
import com.fooddelivery.food_delivery_backend.payment.entity.Payment;
import com.fooddelivery.food_delivery_backend.payment.mapper.PaymentMapper;
import com.fooddelivery.food_delivery_backend.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminMonitoringServiceImpl implements AdminMonitoringService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    public List<OrderResponse> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        return OrderMapper.toResponse(order);
    }

    @Override
    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toResponse)
                .toList();
    }

    @Override
    public PaymentResponse getPaymentById(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment not found"));

        return PaymentMapper.toResponse(payment);
    }

    @Override
    public List<DeliveryResponse> getAllDeliveries() {

        return deliveryRepository.findAll()
                .stream()
                .map(DeliveryMapper::toResponse)
                .toList();
    }

    @Override
    public DeliveryResponse getDeliveryById(Long deliveryId) {

        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Delivery not found"));

        return DeliveryMapper.toResponse(delivery);
    }
}