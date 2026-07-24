package com.fooddelivery.food_delivery_backend.cart.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponse {

    private Long id;

    private Long menuItemId;

    private String menuItemName;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal totalPrice;
}