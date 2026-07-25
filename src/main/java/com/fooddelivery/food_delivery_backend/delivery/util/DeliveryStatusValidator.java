package com.fooddelivery.food_delivery_backend.delivery.util;

import com.fooddelivery.food_delivery_backend.delivery.enums.DeliveryStatus;

import java.util.Map;
import java.util.Set;

public final class DeliveryStatusValidator {

    private DeliveryStatusValidator() {
    }

    private static final Map<DeliveryStatus, Set<DeliveryStatus>> VALID_TRANSITIONS = Map.of(

            DeliveryStatus.ASSIGNED,
            Set.of(DeliveryStatus.ACCEPTED, DeliveryStatus.CANCELLED),

            DeliveryStatus.ACCEPTED,
            Set.of(DeliveryStatus.ARRIVED_AT_RESTAURANT),

            DeliveryStatus.ARRIVED_AT_RESTAURANT,
            Set.of(DeliveryStatus.PICKED_UP),

            DeliveryStatus.PICKED_UP,
            Set.of(DeliveryStatus.DELIVERED)
    );

    public static boolean isValidTransition(DeliveryStatus currentStatus,
                                            DeliveryStatus newStatus) {

        return VALID_TRANSITIONS
                .getOrDefault(currentStatus, Set.of())
                .contains(newStatus);
    }
}