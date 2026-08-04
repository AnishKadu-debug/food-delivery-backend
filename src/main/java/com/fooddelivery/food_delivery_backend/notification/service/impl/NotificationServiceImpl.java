package com.fooddelivery.food_delivery_backend.notification.service.impl;

import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.notification.dto.NotificationResponse;
import com.fooddelivery.food_delivery_backend.notification.entity.Notification;
import com.fooddelivery.food_delivery_backend.notification.enums.NotificationType;
import com.fooddelivery.food_delivery_backend.notification.mapper.NotificationMapper;
import com.fooddelivery.food_delivery_backend.notification.repository.NotificationRepository;
import com.fooddelivery.food_delivery_backend.notification.service.NotificationService;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final CurrentUserService currentUserService;

    @Override
    public void createNotification(
            User user,
            String title,
            String message,
            NotificationType type) {
        Notification notification = Notification.builder()
                .user(user)
                .title(title)
                .message(message)
                .type(type)
                .build();

        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationResponse> getMyNotifications() {

        User currentUser = currentUserService.getCurrentUser();

        return notificationRepository
                .findByUserOrderByCreatedAtDesc(currentUser)
                .stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    public List<NotificationResponse> getUnreadNotifications() {

        User currentUser = currentUserService.getCurrentUser();

        return notificationRepository
                .findByUserAndIsReadFalseOrderByCreatedAtDesc(currentUser)
                .stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    public NotificationResponse markAsRead(Long notificationId) {

        User currentUser = currentUserService.getCurrentUser();

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found"));

        if (!notification.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException(
                    "You cannot update this notification");
        }

        notification.setIsRead(true);

        Notification savedNotification =
                notificationRepository.save(notification);

        return NotificationMapper.toResponse(savedNotification);
    }

    @Override
    public void markAllAsRead() {

        User currentUser = currentUserService.getCurrentUser();

        List<Notification> notifications =
                notificationRepository
                        .findByUserAndIsReadFalseOrderByCreatedAtDesc(currentUser);

        notifications.forEach(notification ->
                notification.setIsRead(true));

        notificationRepository.saveAll(notifications);
    }
}