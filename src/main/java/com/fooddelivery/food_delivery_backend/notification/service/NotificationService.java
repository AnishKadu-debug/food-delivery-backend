package com.fooddelivery.food_delivery_backend.notification.service;

import com.fooddelivery.food_delivery_backend.notification.dto.NotificationResponse;
import com.fooddelivery.food_delivery_backend.notification.entity.Notification;
import com.fooddelivery.food_delivery_backend.notification.enums.NotificationType;
import com.fooddelivery.food_delivery_backend.user.entity.User;

import java.util.List;

public interface NotificationService {

    void createNotification(
            User user,
            String title,
            String message,
            NotificationType type
    );

    List<NotificationResponse> getMyNotifications();

    List<NotificationResponse> getUnreadNotifications();

    NotificationResponse markAsRead(Long notificationId);

    void markAllAsRead();
}