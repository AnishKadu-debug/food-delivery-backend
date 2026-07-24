package com.fooddelivery.food_delivery_backend.cart.repository;

import com.fooddelivery.food_delivery_backend.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);

}