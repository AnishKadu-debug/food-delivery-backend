package com.fooddelivery.food_delivery_backend.cart.service;

import com.fooddelivery.food_delivery_backend.cart.dto.AddCartItemRequest;
import com.fooddelivery.food_delivery_backend.cart.dto.CartResponse;
import com.fooddelivery.food_delivery_backend.cart.dto.UpdateCartItemRequest;

public interface CartService {

    CartResponse addItem(AddCartItemRequest request);

    CartResponse getMyCart();

    CartResponse updateItem(Long cartItemId,
                            UpdateCartItemRequest request);

    void removeItem(Long cartItemId);

    void clearCart();
}