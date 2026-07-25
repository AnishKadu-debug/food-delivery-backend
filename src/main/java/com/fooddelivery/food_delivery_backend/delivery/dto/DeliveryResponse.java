package com.fooddelivery.food_delivery_backend.delivery.dto;

import com.fooddelivery.food_delivery_backend.delivery.enums.DeliveryStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryResponse {

    private Long id;

    private Long orderId;

    private Long deliveryPartnerId;

    private String deliveryPartnerName;

    private DeliveryStatus status;

    private LocalDateTime assignedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime arrivedAt;

    private LocalDateTime pickedUpAt;

    private LocalDateTime deliveredAt;

}