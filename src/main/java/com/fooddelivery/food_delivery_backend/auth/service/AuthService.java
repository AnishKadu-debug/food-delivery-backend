package com.fooddelivery.food_delivery_backend.auth.service;

import com.fooddelivery.food_delivery_backend.user.dto.AuthResponse;
import com.fooddelivery.food_delivery_backend.user.dto.LoginRequest;
import com.fooddelivery.food_delivery_backend.user.dto.RegisterRequest;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}