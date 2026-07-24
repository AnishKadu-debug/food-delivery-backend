package com.fooddelivery.food_delivery_backend.menu.service;

import com.fooddelivery.food_delivery_backend.menu.dto.CreateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.dto.MenuItemResponse;
import com.fooddelivery.food_delivery_backend.menu.dto.UpdateMenuItemRequest;

import java.util.List;

public interface MenuItemService {

    MenuItemResponse createMenuItem(CreateMenuItemRequest request);

    MenuItemResponse updateMenuItem(Long id,
                                    UpdateMenuItemRequest request);

    MenuItemResponse getMenuItem(Long id);

    List<MenuItemResponse> getRestaurantMenu(Long restaurantId);

    List<MenuItemResponse> getAvailableMenu(Long restaurantId);

    List<MenuItemResponse> searchMenuItems(String keyword);

    void deleteMenuItem(Long id);
}