package com.fooddelivery.food_delivery_backend.cart.repository;

import com.fooddelivery.food_delivery_backend.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndMenuItemId(Long cartId,
                                                 Long menuItemId);

}