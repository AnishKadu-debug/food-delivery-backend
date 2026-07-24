package com.fooddelivery.food_delivery_backend.restaurant.repository;

import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Page<Restaurant> findByNameContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );
}