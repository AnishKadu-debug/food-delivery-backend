package com.fooddelivery.food_delivery_backend.admin.service.impl;

import com.fooddelivery.food_delivery_backend.admin.dto.AdminDashboardResponse;
import com.fooddelivery.food_delivery_backend.admin.service.AdminDashboardService;
import com.fooddelivery.food_delivery_backend.delivery.repository.DeliveryRepository;
import com.fooddelivery.food_delivery_backend.order.repository.OrderRepository;
import com.fooddelivery.food_delivery_backend.payment.repository.PaymentRepository;
import com.fooddelivery.food_delivery_backend.restaurant.enums.RestaurantStatus;
import com.fooddelivery.food_delivery_backend.restaurant.repository.RestaurantRepository;
import com.fooddelivery.food_delivery_backend.user.enums.Role;
import com.fooddelivery.food_delivery_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public AdminDashboardResponse getDashboard() {

        return AdminDashboardResponse.builder()
                .totalUsers(userRepository.count())
                .totalCustomers(userRepository.countByRole(Role.CUSTOMER))
                .totalOwners(userRepository.countByRole(Role.OWNER))
                .totalDeliveryPartners(userRepository.countByRole(Role.DELIVERY_PARTNER))
                .totalRestaurants(restaurantRepository.count())
                .pendingRestaurants(
                        restaurantRepository.countByStatus(RestaurantStatus.PENDING)
                )
                .approvedRestaurants(
                        restaurantRepository.countByStatus(RestaurantStatus.APPROVED)
                )
                .totalOrders(orderRepository.count())
                .totalDeliveries(deliveryRepository.count())
                .totalPayments(paymentRepository.count())
                .build();
    }
}