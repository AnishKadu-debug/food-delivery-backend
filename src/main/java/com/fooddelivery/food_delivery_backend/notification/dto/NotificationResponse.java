package com.fooddelivery.food_delivery_backend.notification.dto;

import com.fooddelivery.food_delivery_backend.notification.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents a notification returned by the API.")
public class NotificationResponse {

    @Schema(
            description = "Unique identifier of the notification.",
            example = "25"
    )
    private Long id;

    @Schema(
            description = "Notification title.",
            example = "Order Delivered"
    )
    private String title;

    @Schema(
            description = "Detailed notification message.",
            example = "Your order #101 has been delivered successfully."
    )
    private String message;

    @Schema(
            description = "Type of notification.",
            example = "ORDER"
    )
    private NotificationType type;

    @Schema(
            description = "Whether the notification has been read.",
            example = "false"
    )
    private Boolean isRead;

    @Schema(
            description = "Date and time when the notification was created."
    )
    private LocalDateTime createdAt;
}