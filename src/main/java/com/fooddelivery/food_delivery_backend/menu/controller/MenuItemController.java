package com.fooddelivery.food_delivery_backend.menu.controller;

import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import com.fooddelivery.food_delivery_backend.menu.dto.CreateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.dto.MenuItemResponse;
import com.fooddelivery.food_delivery_backend.menu.dto.UpdateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @PostMapping
    public ApiResponse<MenuItemResponse> createMenuItem(
            @Valid @RequestBody CreateMenuItemRequest request) {

        return ApiResponse.success(
                "Menu item created successfully",
                menuItemService.createMenuItem(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<MenuItemResponse> updateMenuItem(
            @PathVariable Long id,
            @RequestBody UpdateMenuItemRequest request) {

        return ApiResponse.success(
                "Menu item updated successfully",
                menuItemService.updateMenuItem(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<MenuItemResponse> getMenuItem(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Menu item fetched successfully",
                menuItemService.getMenuItem(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ApiResponse<List<MenuItemResponse>> getRestaurantMenu(
            @PathVariable Long restaurantId) {

        return ApiResponse.success(
                "Menu fetched successfully",
                menuItemService.getRestaurantMenu(restaurantId));
    }

    @GetMapping("/restaurant/{restaurantId}/available")
    public ApiResponse<List<MenuItemResponse>> getAvailableMenu(
            @PathVariable Long restaurantId) {

        return ApiResponse.success(
                "Available menu fetched successfully",
                menuItemService.getAvailableMenu(restaurantId));
    }

    @GetMapping("/search")
    public ApiResponse<List<MenuItemResponse>> searchMenu(
            @RequestParam String keyword) {

        return ApiResponse.success(
                "Menu items fetched successfully",
                menuItemService.searchMenuItems(keyword));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteMenuItem(
            @PathVariable Long id) {

        menuItemService.deleteMenuItem(id);

        return ApiResponse.success(
                "Menu item deleted successfully",
                null);
    }
}