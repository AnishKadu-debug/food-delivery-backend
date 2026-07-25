package com.fooddelivery.food_delivery_backend.menu.dto;

import com.fooddelivery.food_delivery_backend.menu.enums.MenuCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateMenuItemRequest {

    @NotNull
    private Long restaurantId;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull
    private MenuCategory category;

    private boolean vegetarian;

    private boolean available = true;

    private String imageUrl;
}