package com.fooddelivery.food_delivery_backend.menu.dto;

import com.fooddelivery.food_delivery_backend.menu.enums.MenuCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Request payload for creating a new menu item.")
public class CreateMenuItemRequest {

    @Schema(
            description = "Unique identifier of the restaurant.",
            example = "1"
    )
    @NotNull
    private Long restaurantId;

    @Schema(
            description = "Name of the menu item.",
            example = "Paneer Butter Masala"
    )
    @NotBlank
    private String name;

    @Schema(
            description = "Short description of the menu item.",
            example = "Creamy tomato gravy with paneer cubes."
    )
    private String description;

    @Schema(
            description = "Price of the menu item.",
            example = "299.00"
    )
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @Schema(
            description = "Category of the menu item.",
            example = "MAIN_COURSE"
    )
    @NotNull
    private MenuCategory category;

    @Schema(
            description = "Whether the menu item is vegetarian.",
            example = "true"
    )
    private boolean vegetarian;

    @Schema(
            description = "Whether the menu item is currently available.",
            example = "true"
    )
    private boolean available = true;

    @Schema(
            description = "Image URL of the menu item.",
            example = "https://example.com/images/paneer.jpg"
    )
    private String imageUrl;
}