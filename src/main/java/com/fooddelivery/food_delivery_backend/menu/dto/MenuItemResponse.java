package com.fooddelivery.food_delivery_backend.menu.dto;

import com.fooddelivery.food_delivery_backend.menu.enums.MenuCategory;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MenuItemResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private MenuCategory category;

    private boolean vegetarian;

    private boolean available;

    private String imageUrl;

    private Long restaurantId;

    private String restaurantName;
}