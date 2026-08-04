package com.fooddelivery.food_delivery_backend.admin.service;

import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;

import java.util.List;

public interface AdminUserService {

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long userId);

    UserResponse activateUser(Long userId);

    UserResponse deactivateUser(Long userId);

}