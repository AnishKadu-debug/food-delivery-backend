package com.fooddelivery.food_delivery_backend.restaurant.repository;

import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;
import com.fooddelivery.food_delivery_backend.restaurant.enums.RestaurantStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Page<Restaurant> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<Restaurant> findByCityContainingIgnoreCase(String city, Pageable pageable);

    Page<Restaurant> findByStatus(RestaurantStatus status, Pageable pageable);

    Optional<Restaurant> findByEmail(String email);

    Optional<Restaurant> findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    long countByStatus(RestaurantStatus status);
}