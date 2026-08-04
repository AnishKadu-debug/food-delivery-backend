package com.fooddelivery.food_delivery_backend.restaurant.service.impl;

import com.fooddelivery.food_delivery_backend.common.exception.DuplicateResourceException;
import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.restaurant.dto.CreateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.dto.RestaurantResponse;
import com.fooddelivery.food_delivery_backend.restaurant.dto.UpdateRestaurantRequest;
import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;
import com.fooddelivery.food_delivery_backend.restaurant.enums.RestaurantStatus;
import com.fooddelivery.food_delivery_backend.restaurant.mapper.RestaurantMapper;
import com.fooddelivery.food_delivery_backend.restaurant.repository.RestaurantRepository;
import com.fooddelivery.food_delivery_backend.restaurant.service.RestaurantService;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CurrentUserService currentUserService;

    @Override
    public RestaurantResponse createRestaurant(CreateRestaurantRequest request) {

        User owner = currentUserService.getCurrentUser();

        if (owner.getRole() != Role.OWNER) {
            throw new ForbiddenException("Only restaurant owners can create restaurants.");
        }

        if (restaurantRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Restaurant email already exists.");
        }

        if (restaurantRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException("Restaurant phone already exists.");
        }

        Restaurant restaurant = RestaurantMapper.toEntity(request);

        restaurant.setOwner(owner);
        restaurant.setStatus(RestaurantStatus.PENDING);
        restaurant.setOpen(false);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponse(restaurant);
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id,
                                               UpdateRestaurantRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found"));

        if (currentUser.getRole() != Role.ADMIN &&
                !restaurant.getOwner().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You are not allowed to update this restaurant.");
        }

        if (request.getEmail() != null &&
                !request.getEmail().equals(restaurant.getEmail())) {

            restaurantRepository.findByEmail(request.getEmail())
                    .ifPresent(r -> {
                        throw new DuplicateResourceException("Restaurant email already exists.");
                    });
        }

        if (request.getPhone() != null &&
                !request.getPhone().equals(restaurant.getPhone())) {

            restaurantRepository.findByPhone(request.getPhone())
                    .ifPresent(r -> {
                        throw new DuplicateResourceException("Restaurant phone already exists.");
                    });
        }

        RestaurantMapper.updateEntity(restaurant, request);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponse(restaurant);
    }

    @Override
    public RestaurantResponse getRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found"));

        return RestaurantMapper.toResponse(restaurant);
    }

    @Override
    public Page<RestaurantResponse> getAllRestaurants(int page,
                                                      int size,
                                                      String sortBy) {

        Pageable pageable =
                PageRequest.of(page, size, Sort.by(sortBy));

        return restaurantRepository.findAll(pageable)
                .map(RestaurantMapper::toResponse);
    }

    @Override
    public void deleteRestaurant(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found"));

        if (currentUser.getRole() != Role.ADMIN &&
                !restaurant.getOwner().getId().equals(currentUser.getId())) {

            throw new ForbiddenException(
                    "You are not allowed to delete this restaurant.");
        }

        restaurantRepository.delete(restaurant);
    }

    @Override
    public Page<RestaurantResponse> searchRestaurants(String keyword,
                                                      int page,
                                                      int size) {

        Pageable pageable = PageRequest.of(page, size);

        return restaurantRepository
                .findByNameContainingIgnoreCase(keyword, pageable)
                .map(RestaurantMapper::toResponse);
    }
    @Override
    public Page<RestaurantResponse> getRestaurantsByCity(String city,
                                                         int page,
                                                         int size) {

        Pageable pageable = PageRequest.of(page, size);

        return restaurantRepository.findByCityContainingIgnoreCase(city, pageable)
                .map(RestaurantMapper::toResponse);
    }

    @Override
    public Page<RestaurantResponse> getPendingRestaurants(int page,
                                                          int size) {

        Pageable pageable = PageRequest.of(page, size);

        return restaurantRepository.findByStatus(
                        RestaurantStatus.PENDING,
                        pageable)
                .map(RestaurantMapper::toResponse);
    }

    @Override
    public RestaurantResponse approveRestaurant(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        if (currentUser.getRole() != Role.ADMIN) {
            throw new ForbiddenException("Only admins can approve restaurants.");
        }

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found"));

        restaurant.setStatus(RestaurantStatus.APPROVED);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponse(restaurant);
    }

    @Override
    public RestaurantResponse rejectRestaurant(Long id) {
        User currentUser = currentUserService.getCurrentUser();

        if (currentUser.getRole() != Role.ADMIN) {
            throw new ForbiddenException("Only admins can reject restaurants.");
        }


        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found"));

        restaurant.setStatus(RestaurantStatus.REJECTED);
        restaurant.setOpen(false);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponse(restaurant);
    }

    @Override
    public RestaurantResponse openRestaurant(Long id) {

        User user = currentUserService.getCurrentUser();

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found"));

        if (user.getRole() != Role.ADMIN &&
                !restaurant.getOwner().getId().equals(user.getId())) {
            throw new ForbiddenException("Access denied.");
        }

        if (restaurant.getStatus() != RestaurantStatus.APPROVED) {
            throw new ForbiddenException("Restaurant is not approved.");
        }

        restaurant.setOpen(true);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponse(restaurant);
    }

    @Override
    public RestaurantResponse closeRestaurant(Long id) {

        User user = currentUserService.getCurrentUser();

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found"));

        if (user.getRole() != Role.ADMIN &&
                !restaurant.getOwner().getId().equals(user.getId())) {
            throw new ForbiddenException("Access denied.");
        }

        restaurant.setOpen(false);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponse(restaurant);
    }
}