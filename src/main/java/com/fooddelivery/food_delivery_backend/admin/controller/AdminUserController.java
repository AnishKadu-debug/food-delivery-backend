package com.fooddelivery.food_delivery_backend.admin.controller;

import com.fooddelivery.food_delivery_backend.admin.service.AdminUserService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {

        return ApiResponse.success(
                "Users fetched successfully",
                adminUserService.getAllUsers()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "User fetched successfully",
                adminUserService.getUserById(id)
        );
    }

    @PatchMapping("/{id}/activate")
    public ApiResponse<UserResponse> activateUser(
            @PathVariable Long id) {

        return ApiResponse.success(
                "User activated successfully",
                adminUserService.activateUser(id)
        );
    }

    @PatchMapping("/{id}/deactivate")
    public ApiResponse<UserResponse> deactivateUser(
            @PathVariable Long id) {

        return ApiResponse.success(
                "User deactivated successfully",
                adminUserService.deactivateUser(id)
        );
    }
}