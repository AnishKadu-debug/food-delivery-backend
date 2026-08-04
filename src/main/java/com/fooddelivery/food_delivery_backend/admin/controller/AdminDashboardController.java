package com.fooddelivery.food_delivery_backend.admin.controller;

import com.fooddelivery.food_delivery_backend.admin.dto.AdminDashboardResponse;
import com.fooddelivery.food_delivery_backend.admin.service.AdminDashboardService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping
    public ApiResponse<AdminDashboardResponse> getDashboard() {

        return ApiResponse.success(
                "Dashboard fetched successfully",
                adminDashboardService.getDashboard()
        );
    }
}