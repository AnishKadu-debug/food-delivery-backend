package com.fooddelivery.food_delivery_backend.notification.repository;

import com.fooddelivery.food_delivery_backend.notification.entity.Notification;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserOrderByCreatedAtDesc(User user);

    List<Notification> findByUserAndIsReadFalseOrderByCreatedAtDesc(User user);

}