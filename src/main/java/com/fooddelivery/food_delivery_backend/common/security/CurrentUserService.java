package com.fooddelivery.food_delivery_backend.common.security;

import com.fooddelivery.food_delivery_backend.common.exception.UnauthorizedException;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserService userService;

    public User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User not authenticated");
        }

        String email = authentication.getName();

        return userService.findByEmail(email)
                .orElseThrow(() ->
                        new UnauthorizedException("User not found"));
    }
}