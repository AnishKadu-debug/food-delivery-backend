package com.fooddelivery.food_delivery_backend.cart.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {

    private Long cartId;

    private List<CartItemResponse> items;

    private Integer totalItems;

    private BigDecimal subtotal;
}