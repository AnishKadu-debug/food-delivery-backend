package com.fooddelivery.food_delivery_backend.auth.service.impl;

import com.fooddelivery.food_delivery_backend.auth.service.AuthService;
import com.fooddelivery.food_delivery_backend.common.mapper.UserMapper;
import com.fooddelivery.food_delivery_backend.user.dto.RegisterRequest;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userService.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userService.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        User user = UserMapper.toEntity(request);

        User savedUser = userService.save(user);

        return UserMapper.toResponse(savedUser);

    }

}