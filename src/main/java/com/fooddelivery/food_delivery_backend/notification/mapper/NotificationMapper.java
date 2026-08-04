package com.fooddelivery.food_delivery_backend.notification.mapper;

import com.fooddelivery.food_delivery_backend.notification.dto.NotificationResponse;
import com.fooddelivery.food_delivery_backend.notification.entity.Notification;

public final class NotificationMapper {

    private NotificationMapper() {
    }

    public static NotificationResponse toResponse(Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .type(notification.getType())
                .isRead(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}