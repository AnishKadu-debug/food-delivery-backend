package com.fooddelivery.food_delivery_backend.menu.mapper;

import com.fooddelivery.food_delivery_backend.menu.dto.CreateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.dto.MenuItemResponse;
import com.fooddelivery.food_delivery_backend.menu.dto.UpdateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.entity.MenuItem;

public class MenuItemMapper {

    private MenuItemMapper() {
    }

    public static MenuItem toEntity(CreateMenuItemRequest request) {

        return MenuItem.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .vegetarian(request.isVegetarian())
                .available(request.isAvailable())
                .imageUrl(request.getImageUrl())
                .build();
    }

    public static void updateEntity(MenuItem item,
                                    UpdateMenuItemRequest request) {

        if (request.getName() != null)
            item.setName(request.getName());

        if (request.getDescription() != null)
            item.setDescription(request.getDescription());

        if (request.getPrice() != null)
            item.setPrice(request.getPrice());

        if (request.getCategory() != null)
            item.setCategory(request.getCategory());

        if (request.getVegetarian() != null)
            item.setVegetarian(request.getVegetarian());

        if (request.getAvailable() != null)
            item.setAvailable(request.getAvailable());

        if (request.getImageUrl() != null)
            item.setImageUrl(request.getImageUrl());
    }

    public static MenuItemResponse toResponse(MenuItem item) {

        return MenuItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .category(item.getCategory())
                .vegetarian(item.isVegetarian())
                .available(item.isAvailable())
                .imageUrl(item.getImageUrl())
                .restaurantId(item.getRestaurant().getId())
                .restaurantName(item.getRestaurant().getName())
                .build();
    }
}