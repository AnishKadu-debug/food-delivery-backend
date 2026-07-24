package com.fooddelivery.food_delivery_backend.cart.mapper;

import com.fooddelivery.food_delivery_backend.cart.dto.CartItemResponse;
import com.fooddelivery.food_delivery_backend.cart.dto.CartResponse;
import com.fooddelivery.food_delivery_backend.cart.entity.Cart;
import com.fooddelivery.food_delivery_backend.cart.entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

public final class CartMapper {

    private CartMapper() {
    }

    public static CartItemResponse toResponse(CartItem cartItem) {

        BigDecimal totalPrice = cartItem.getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        return CartItemResponse.builder()
                .id(cartItem.getId())
                .menuItemId(cartItem.getMenuItem().getId())
                .menuItemName(cartItem.getMenuItem().getName())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .totalPrice(totalPrice)
                .build();
    }

    public static CartResponse toResponse(Cart cart) {

        List<CartItemResponse> items = cart.getCartItems()
                .stream()
                .map(CartMapper::toResponse)
                .toList();

        int totalItems = cart.getCartItems()
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        BigDecimal subtotal = cart.getCartItems()
                .stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(items)
                .totalItems(totalItems)
                .subtotal(subtotal)
                .build();
    }
}