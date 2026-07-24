package com.fooddelivery.food_delivery_backend.menu.service.Impl;

import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.menu.dto.CreateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.dto.MenuItemResponse;
import com.fooddelivery.food_delivery_backend.menu.dto.UpdateMenuItemRequest;
import com.fooddelivery.food_delivery_backend.menu.entity.MenuItem;
import com.fooddelivery.food_delivery_backend.menu.mapper.MenuItemMapper;
import com.fooddelivery.food_delivery_backend.menu.repository.MenuItemRepository;
import com.fooddelivery.food_delivery_backend.menu.service.MenuItemService;
import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;
import com.fooddelivery.food_delivery_backend.restaurant.repository.RestaurantRepository;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final CurrentUserService currentUserService;

    @Override
    public MenuItemResponse createMenuItem(CreateMenuItemRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        if (currentUser.getRole() != Role.ADMIN &&
                !restaurant.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot manage this restaurant.");
        }

        MenuItem item = MenuItemMapper.toEntity(request);
        item.setRestaurant(restaurant);

        item = menuItemRepository.save(item);

        return MenuItemMapper.toResponse(item);
    }

    @Override
    public MenuItemResponse updateMenuItem(Long id,
                                           UpdateMenuItemRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        if (currentUser.getRole() != Role.ADMIN &&
                !item.getRestaurant().getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot manage this menu item.");
        }

        MenuItemMapper.updateEntity(item, request);

        item = menuItemRepository.save(item);

        return MenuItemMapper.toResponse(item);
    }

    @Override
    public MenuItemResponse getMenuItem(Long id) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        return MenuItemMapper.toResponse(item);
    }

    @Override
    public List<MenuItemResponse> getRestaurantMenu(Long restaurantId) {

        return menuItemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(MenuItemMapper::toResponse)
                .toList();
    }

    @Override
    public List<MenuItemResponse> getAvailableMenu(Long restaurantId) {

        return menuItemRepository.findByRestaurantIdAndAvailableTrue(restaurantId)
                .stream()
                .map(MenuItemMapper::toResponse)
                .toList();
    }

    @Override
    public List<MenuItemResponse> searchMenuItems(String keyword) {

        return menuItemRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(MenuItemMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteMenuItem(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        if (currentUser.getRole() != Role.ADMIN &&
                !item.getRestaurant().getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot delete this menu item.");
        }

        menuItemRepository.delete(item);
    }
}