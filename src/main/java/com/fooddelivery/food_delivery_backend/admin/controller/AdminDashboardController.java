package com.fooddelivery.food_delivery_backend.admin.controller;

import com.fooddelivery.food_delivery_backend.admin.dto.AdminDashboardResponse;
import com.fooddelivery.food_delivery_backend.admin.service.AdminDashboardService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
@Tag(
        name = "Admin Dashboard",
        description = "Administrative dashboard APIs for viewing overall platform statistics."
)
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @Operation(
            summary = "Get admin dashboard",
            description = "Returns dashboard statistics including users, restaurants, orders, payments, and deliveries."
    )
    @GetMapping
    public ApiResponse<AdminDashboardResponse> getDashboard() {

        return ApiResponse.success(
                "Dashboard fetched successfully",
                adminDashboardService.getDashboard()
        );
    }
}