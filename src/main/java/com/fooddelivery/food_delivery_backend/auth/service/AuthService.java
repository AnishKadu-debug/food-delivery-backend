package com.fooddelivery.food_delivery_backend.auth.service;

import com.fooddelivery.food_delivery_backend.user.dto.RegisterRequest;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

}