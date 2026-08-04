package com.fooddelivery.food_delivery_backend.notification.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.notification.dto.NotificationResponse;
import com.fooddelivery.food_delivery_backend.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ApiResponse<List<NotificationResponse>> getMyNotifications() {

        return ApiResponse.success(
                "Notifications fetched successfully",
                notificationService.getMyNotifications()
        );
    }

    @GetMapping("/unread")
    public ApiResponse<List<NotificationResponse>> getUnreadNotifications() {

        return ApiResponse.success(
                "Unread notifications fetched successfully",
                notificationService.getUnreadNotifications()
        );
    }

    @PutMapping("/{id}/read")
    public ApiResponse<NotificationResponse> markAsRead(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Notification marked as read",
                notificationService.markAsRead(id)
        );
    }

    @PutMapping("/read-all")
    public ApiResponse<Void> markAllAsRead() {

        notificationService.markAllAsRead();

        return ApiResponse.success(
                "All notifications marked as read",
                null
        );
    }
}