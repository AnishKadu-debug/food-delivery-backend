package com.fooddelivery.food_delivery_backend.common.mapper;

import com.fooddelivery.food_delivery_backend.user.dto.RegisterRequest;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.enums.AuthProvider;
import com.fooddelivery.food_delivery_backend.user.enums.Role;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(RegisterRequest request) {

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .role(Role.CUSTOMER)
                .provider(AuthProvider.LOCAL)
                .active(true)
                .emailVerified(false)
                .build();

    }

    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();

    }

}