package com.fooddelivery.food_delivery_backend.menu.dto;

import com.fooddelivery.food_delivery_backend.menu.enums.MenuCategory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateMenuItemRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private MenuCategory category;

    private Boolean vegetarian;

    private Boolean available;

    private String imageUrl;
}