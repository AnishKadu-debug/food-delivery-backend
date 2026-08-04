package com.fooddelivery.food_delivery_backend.delivery.dto;

import com.fooddelivery.food_delivery_backend.delivery.enums.DeliveryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents a delivery returned by the API.")
public class DeliveryResponse {

    @Schema(
            description = "Unique identifier of the delivery.",
            example = "20"
    )
    private Long id;

    @Schema(
            description = "Identifier of the associated order.",
            example = "101"
    )
    private Long orderId;

    @Schema(
            description = "Identifier of the assigned delivery partner.",
            example = "8"
    )
    private Long deliveryPartnerId;

    @Schema(
            description = "Name of the assigned delivery partner.",
            example = "Amit Kumar"
    )
    private String deliveryPartnerName;

    @Schema(
            description = "Current delivery status.",
            example = "PICKED_UP"
    )
    private DeliveryStatus status;

    @Schema(
            description = "Date and time when the delivery was assigned."
    )
    private LocalDateTime assignedAt;

    @Schema(
            description = "Date and time when the delivery partner accepted the delivery."
    )
    private LocalDateTime acceptedAt;

    @Schema(
            description = "Date and time when the delivery partner arrived at the restaurant."
    )
    private LocalDateTime arrivedAt;

    @Schema(
            description = "Date and time when the order was picked up."
    )
    private LocalDateTime pickedUpAt;

    @Schema(
            description = "Date and time when the order was delivered."
    )
    private LocalDateTime deliveredAt;
}