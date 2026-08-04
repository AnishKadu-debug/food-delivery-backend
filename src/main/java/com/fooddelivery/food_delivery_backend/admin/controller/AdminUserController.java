package com.fooddelivery.food_delivery_backend.admin.controller;

import com.fooddelivery.food_delivery_backend.admin.service.AdminUserService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@Tag(
        name = "Admin User Management",
        description = "Administrative APIs for managing user accounts."
)
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Operation(
            summary = "Get all users",
            description = "Returns all registered users."
    )
    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {

        return ApiResponse.success(
                "Users fetched successfully",
                adminUserService.getAllUsers()
        );
    }

    @Operation(
            summary = "Get user by ID",
            description = "Returns detailed information about a specific user."
    )
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(
            @PathVariable Long id) {

        return ApiResponse.success(
                "User fetched successfully",
                adminUserService.getUserById(id)
        );
    }

    @Operation(
            summary = "Activate user",
            description = "Activates a previously deactivated user account."
    )
    @PatchMapping("/{id}/activate")
    public ApiResponse<UserResponse> activateUser(
            @PathVariable Long id) {

        return ApiResponse.success(
                "User activated successfully",
                adminUserService.activateUser(id)
        );
    }

    @Operation(
            summary = "Deactivate user",
            description = "Deactivates an active user account."
    )
    @PatchMapping("/{id}/deactivate")
    public ApiResponse<UserResponse> deactivateUser(
            @PathVariable Long id) {

        return ApiResponse.success(
                "User deactivated successfully",
                adminUserService.deactivateUser(id)
        );
    }
}