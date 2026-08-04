package com.fooddelivery.food_delivery_backend.notification.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.notification.dto.NotificationResponse;
import com.fooddelivery.food_delivery_backend.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(
        name = "Notifications",
        description = "APIs for viewing and managing user notifications."
)
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(
            summary = "Get my notifications",
            description = "Returns all notifications of the authenticated user."
    )
    @GetMapping
    public ApiResponse<List<NotificationResponse>> getMyNotifications() {

        return ApiResponse.success(
                "Notifications fetched successfully",
                notificationService.getMyNotifications()
        );
    }

    @Operation(
            summary = "Get unread notifications",
            description = "Returns only unread notifications of the authenticated user."
    )
    @GetMapping("/unread")
    public ApiResponse<List<NotificationResponse>> getUnreadNotifications() {

        return ApiResponse.success(
                "Unread notifications fetched successfully",
                notificationService.getUnreadNotifications()
        );
    }

    @Operation(
            summary = "Mark notification as read",
            description = "Marks a specific notification as read."
    )
    @PutMapping("/{id}/read")
    public ApiResponse<NotificationResponse> markAsRead(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Notification marked as read",
                notificationService.markAsRead(id)
        );
    }

    @Operation(
            summary = "Mark all notifications as read",
            description = "Marks every notification of the authenticated user as read."
    )
    @PutMapping("/read-all")
    public ApiResponse<Void> markAllAsRead() {

        notificationService.markAllAsRead();

        return ApiResponse.success(
                "All notifications marked as read",
                null
        );
    }
}