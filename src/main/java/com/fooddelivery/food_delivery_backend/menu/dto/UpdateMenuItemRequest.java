package com.fooddelivery.food_delivery_backend.menu.dto;

import com.fooddelivery.food_delivery_backend.menu.enums.MenuCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Request payload for updating an existing menu item.")
public class UpdateMenuItemRequest {

    @Schema(
            description = "Updated menu item name.",
            example = "Paneer Butter Masala Deluxe"
    )
    private String name;

    @Schema(
            description = "Updated description of the menu item.",
            example = "Rich creamy tomato gravy with premium paneer cubes."
    )
    private String description;

    @Schema(
            description = "Updated price of the menu item.",
            example = "349.00"
    )
    private BigDecimal price;

    @Schema(
            description = "Updated menu category.",
            example = "MAIN_COURSE"
    )
    private MenuCategory category;

    @Schema(
            description = "Whether the menu item is vegetarian.",
            example = "true"
    )
    private Boolean vegetarian;

    @Schema(
            description = "Whether the menu item is available for ordering.",
            example = "true"
    )
    private Boolean available;

    @Schema(
            description = "Updated image URL of the menu item.",
            example = "https://example.com/images/paneer-deluxe.jpg"
    )
    private String imageUrl;
}