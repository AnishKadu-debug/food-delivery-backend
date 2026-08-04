package com.fooddelivery.food_delivery_backend.menu.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.menu.dto.CreateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.dto.MenuItemResponse;
import com.fooddelivery.food_delivery_backend.menu.dto.UpdateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.service.MenuItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Tag(
        name = "Menu",
        description = "APIs for managing restaurant menu items and browsing restaurant menus."
)
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Operation(
            summary = "Create a menu item",
            description = "Creates a new menu item for the authenticated restaurant owner's restaurant."
    )
    @PostMapping
    public ApiResponse<MenuItemResponse> createMenuItem(
            @Valid @RequestBody CreateMenuItemRequest request) {

        return ApiResponse.success(
                "Menu item created successfully",
                menuItemService.createMenuItem(request)
        );
    }

    @Operation(
            summary = "Update a menu item",
            description = "Updates an existing menu item belonging to the authenticated restaurant owner."
    )
    @PutMapping("/{id}")
    public ApiResponse<MenuItemResponse> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMenuItemRequest request) {

        return ApiResponse.success(
                "Menu item updated successfully",
                menuItemService.updateMenuItem(id, request)
        );
    }

    @Operation(
            summary = "Get menu item by ID",
            description = "Returns detailed information about a specific menu item."
    )
    @GetMapping("/{id}")
    public ApiResponse<MenuItemResponse> getMenuItem(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Menu item fetched successfully",
                menuItemService.getMenuItem(id)
        );
    }

    @Operation(
            summary = "Get restaurant menu",
            description = "Returns all menu items belonging to a restaurant."
    )
    @GetMapping("/restaurant/{restaurantId}")
    public ApiResponse<List<MenuItemResponse>> getRestaurantMenu(
            @PathVariable Long restaurantId) {

        return ApiResponse.success(
                "Menu fetched successfully",
                menuItemService.getRestaurantMenu(restaurantId)
        );
    }

    @Operation(
            summary = "Get available menu items",
            description = "Returns only the menu items that are currently available for ordering."
    )
    @GetMapping("/restaurant/{restaurantId}/available")
    public ApiResponse<List<MenuItemResponse>> getAvailableMenu(
            @PathVariable Long restaurantId) {

        return ApiResponse.success(
                "Available menu fetched successfully",
                menuItemService.getAvailableMenu(restaurantId)
        );
    }

    @Operation(
            summary = "Search menu items",
            description = "Searches menu items using a keyword."
    )
    @GetMapping("/search")
    public ApiResponse<List<MenuItemResponse>> searchMenu(
            @RequestParam String keyword) {

        return ApiResponse.success(
                "Menu items fetched successfully",
                menuItemService.searchMenuItems(keyword)
        );
    }

    @Operation(
            summary = "Delete a menu item",
            description = "Deletes a menu item belonging to the authenticated restaurant owner."
    )
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteMenuItem(
            @PathVariable Long id) {

        menuItemService.deleteMenuItem(id);

        return ApiResponse.success(
                "Menu item deleted successfully",
                null
        );
    }
}