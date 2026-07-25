package com.fooddelivery.food_delivery_backend.delivery.repository;

import com.fooddelivery.food_delivery_backend.delivery.entity.Delivery;
import com.fooddelivery.food_delivery_backend.delivery.enums.DeliveryStatus;
import com.fooddelivery.food_delivery_backend.order.entity.Order;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Optional<Delivery> findByOrder(Order order);

    List<Delivery> findByDeliveryPartner(User deliveryPartner);

    boolean existsByDeliveryPartnerAndStatusIn(
            User deliveryPartner,
            Collection<DeliveryStatus> statuses
    );

}