package com.fooddelivery.food_delivery_backend.menu.repository;

import com.fooddelivery.food_delivery_backend.menu.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByRestaurantId(Long restaurantId);

    List<MenuItem> findByRestaurantIdAndAvailableTrue(Long restaurantId);

    List<MenuItem> findByNameContainingIgnoreCase(String keyword);
}