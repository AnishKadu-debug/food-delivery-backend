package com.fooddelivery.food_delivery_backend.menu.dto;

import com.fooddelivery.food_delivery_backend.menu.enums.MenuCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@Schema(description = "Represents a menu item returned by the API.")
public class MenuItemResponse {

    @Schema(
            description = "Unique identifier of the menu item.",
            example = "15"
    )
    private Long id;

    @Schema(
            description = "Name of the menu item.",
            example = "Paneer Butter Masala"
    )
    private String name;

    @Schema(
            description = "Description of the menu item.",
            example = "Creamy tomato gravy with paneer cubes."
    )
    private String description;

    @Schema(
            description = "Price of the menu item.",
            example = "299.00"
    )
    private BigDecimal price;

    @Schema(
            description = "Category of the menu item.",
            example = "MAIN_COURSE"
    )
    private MenuCategory category;

    @Schema(
            description = "Whether the menu item is vegetarian.",
            example = "true"
    )
    private boolean vegetarian;

    @Schema(
            description = "Whether the menu item is available for ordering.",
            example = "true"
    )
    private boolean available;

    @Schema(
            description = "Image URL of the menu item.",
            example = "https://example.com/images/paneer.jpg"
    )
    private String imageUrl;

    @Schema(
            description = "Restaurant identifier.",
            example = "1"
    )
    private Long restaurantId;

    @Schema(
            description = "Restaurant name.",
            example = "Spice Garden"
    )
    private String restaurantName;
}