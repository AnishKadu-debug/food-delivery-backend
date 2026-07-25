package com.fooddelivery.food_delivery_backend.delivery.mapper;

import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;
import com.fooddelivery.food_delivery_backend.delivery.entity.Delivery;

public class DeliveryMapper {

    private DeliveryMapper() {
    }

    public static DeliveryResponse toResponse(Delivery delivery) {

        return DeliveryResponse.builder()
                .id(delivery.getId())
                .orderId(delivery.getOrder().getId())
                .deliveryPartnerId(delivery.getDeliveryPartner().getId())
                .deliveryPartnerName(delivery.getDeliveryPartner().getName())
                .status(delivery.getStatus())
                .assignedAt(delivery.getAssignedAt())
                .acceptedAt(delivery.getAcceptedAt())
                .arrivedAt(delivery.getArrivedAt())
                .pickedUpAt(delivery.getPickedUpAt())
                .deliveredAt(delivery.getDeliveredAt())
                .build();
    }
}