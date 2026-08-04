package com.fooddelivery.food_delivery_backend.delivery.service.impl;

import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.delivery.dto.AssignDeliveryRequest;
import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;
import com.fooddelivery.food_delivery_backend.delivery.entity.Delivery;
import com.fooddelivery.food_delivery_backend.delivery.enums.DeliveryStatus;
import com.fooddelivery.food_delivery_backend.delivery.mapper.DeliveryMapper;
import com.fooddelivery.food_delivery_backend.delivery.repository.DeliveryRepository;
import com.fooddelivery.food_delivery_backend.delivery.service.DeliveryService;
import com.fooddelivery.food_delivery_backend.delivery.util.DeliveryStatusValidator;
import com.fooddelivery.food_delivery_backend.notification.enums.NotificationType;
import com.fooddelivery.food_delivery_backend.notification.service.NotificationService;
import com.fooddelivery.food_delivery_backend.order.entity.Order;
import com.fooddelivery.food_delivery_backend.order.enums.OrderStatus;
import com.fooddelivery.food_delivery_backend.order.repository.OrderRepository;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.enums.Role;
import com.fooddelivery.food_delivery_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final NotificationService notificationService;

    @Override
    public DeliveryResponse assignDeliveryPartner(Long orderId,
                                                  AssignDeliveryRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        if (!order.getRestaurant().getOwner().getId()
                .equals(currentUser.getId())) {

            throw new ForbiddenException(
                    "You can assign deliveries only for your restaurant");
        }

        if (order.getStatus() != OrderStatus.READY_FOR_PICKUP) {
            throw new IllegalArgumentException(
                    "Order is not ready for pickup");
        }

        if (deliveryRepository.findByOrder(order).isPresent()) {
            throw new IllegalArgumentException(
                    "Delivery already assigned");
        }

        User deliveryPartner = userRepository.findById(
                        request.getDeliveryPartnerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Delivery partner not found"));

        if (deliveryPartner.getRole() != Role.DELIVERY_PARTNER) {
            throw new IllegalArgumentException(
                    "Selected user is not a delivery partner");
        }

        if (deliveryRepository.existsByDeliveryPartnerAndStatusIn(
                deliveryPartner,
                Set.of(
                        DeliveryStatus.ASSIGNED,
                        DeliveryStatus.ACCEPTED,
                        DeliveryStatus.ARRIVED_AT_RESTAURANT,
                        DeliveryStatus.PICKED_UP
                ))) {

            throw new IllegalArgumentException(
                    "Delivery partner already has an active delivery");
        }

        Delivery delivery = Delivery.builder()
                .order(order)
                .deliveryPartner(deliveryPartner)
                .status(DeliveryStatus.ASSIGNED)
                .assignedAt(LocalDateTime.now())
                .build();

        Delivery saved = deliveryRepository.save(delivery);

        order.setDelivery(saved);
        orderRepository.save(order);

        notificationService.createNotification(
                deliveryPartner,
                "New Delivery Assigned",
                "A new delivery has been assigned to you for Order #" + order.getId(),
                NotificationType.DELIVERY_ASSIGNED
        );

        return DeliveryMapper.toResponse(saved);
    }

    @Override
    public List<DeliveryResponse> getMyDeliveries() {

        User currentUser = currentUserService.getCurrentUser();

        return deliveryRepository.findByDeliveryPartner(currentUser)
                .stream()
                .map(DeliveryMapper::toResponse)
                .toList();
    }

    @Override
    public DeliveryResponse getDelivery(Long deliveryId) {

        Delivery delivery = getOwnedDelivery(deliveryId);

        return DeliveryMapper.toResponse(delivery);
    }

    @Override
    public DeliveryResponse acceptDelivery(Long deliveryId) {

        Delivery delivery = getOwnedDelivery(deliveryId);

        validateTransition(delivery, DeliveryStatus.ACCEPTED);

        delivery.setStatus(DeliveryStatus.ACCEPTED);
        delivery.setAcceptedAt(LocalDateTime.now());

        return DeliveryMapper.toResponse(
                deliveryRepository.save(delivery));
    }

    @Override
    public DeliveryResponse arriveAtRestaurant(Long deliveryId) {

        Delivery delivery = getOwnedDelivery(deliveryId);

        validateTransition(delivery, DeliveryStatus.ARRIVED_AT_RESTAURANT);

        delivery.setStatus(DeliveryStatus.ARRIVED_AT_RESTAURANT);
        delivery.setArrivedAt(LocalDateTime.now());

        return DeliveryMapper.toResponse(
                deliveryRepository.save(delivery));
    }

    @Override
    public DeliveryResponse pickupOrder(Long deliveryId) {

        Delivery delivery = getOwnedDelivery(deliveryId);

        validateTransition(delivery, DeliveryStatus.PICKED_UP);

        delivery.setStatus(DeliveryStatus.PICKED_UP);
        delivery.setPickedUpAt(LocalDateTime.now());

        Order order = delivery.getOrder();
        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);

        orderRepository.save(order);

        notificationService.createNotification(
                order.getCustomer(),
                "Order Picked Up",
                "Your order #" + order.getId() + " is on the way.",
                NotificationType.ORDER_PICKED_UP
        );

        return DeliveryMapper.toResponse(
                deliveryRepository.save(delivery));
    }

    @Override
    public DeliveryResponse completeDelivery(Long deliveryId) {

        Delivery delivery = getOwnedDelivery(deliveryId);

        validateTransition(delivery, DeliveryStatus.DELIVERED);

        delivery.setStatus(DeliveryStatus.DELIVERED);
        delivery.setDeliveredAt(LocalDateTime.now());

        Order order = delivery.getOrder();
        order.setStatus(OrderStatus.DELIVERED);

        orderRepository.save(order);

        notificationService.createNotification(
                order.getCustomer(),
                "Order Delivered",
                "Your order #" + order.getId() + " has been delivered.",
                NotificationType.ORDER_DELIVERED
        );

        return DeliveryMapper.toResponse(
                deliveryRepository.save(delivery));
    }

    private Delivery getOwnedDelivery(Long deliveryId) {

        User currentUser = currentUserService.getCurrentUser();

        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Delivery not found"));

        if (!delivery.getDeliveryPartner().getId()
                .equals(currentUser.getId())) {

            throw new ForbiddenException(
                    "You can access only your assigned deliveries");
        }

        return delivery;
    }

    private void validateTransition(Delivery delivery,
                                    DeliveryStatus newStatus) {

        if (!DeliveryStatusValidator.isValidTransition(
                delivery.getStatus(),
                newStatus)) {

            throw new IllegalArgumentException(
                    "Invalid delivery status transition from "
                            + delivery.getStatus()
                            + " to "
                            + newStatus);
        }
    }
}