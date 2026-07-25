package com.fooddelivery.food_delivery_backend.delivery.service;

import com.fooddelivery.food_delivery_backend.delivery.dto.AssignDeliveryRequest;
import com.fooddelivery.food_delivery_backend.delivery.dto.DeliveryResponse;

import java.util.List;

public interface DeliveryService {

    DeliveryResponse assignDeliveryPartner(Long orderId,
                                           AssignDeliveryRequest request);

    List<DeliveryResponse> getMyDeliveries();

    DeliveryResponse getDelivery(Long deliveryId);

    DeliveryResponse acceptDelivery(Long deliveryId);

    DeliveryResponse arriveAtRestaurant(Long deliveryId);

    DeliveryResponse pickupOrder(Long deliveryId);

    DeliveryResponse completeDelivery(Long deliveryId);

}