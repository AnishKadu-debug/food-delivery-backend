package com.fooddelivery.food_delivery_backend.admin.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDashboardResponse {

    private long totalUsers;

    private long totalCustomers;

    private long totalOwners;

    private long totalDeliveryPartners;

    private long totalRestaurants;

    private long pendingRestaurants;

    private long approvedRestaurants;

    private long totalOrders;

    private long totalDeliveries;

    private long totalPayments;
}