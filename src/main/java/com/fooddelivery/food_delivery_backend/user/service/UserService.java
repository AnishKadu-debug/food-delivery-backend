package com.fooddelivery.food_delivery_backend.user.service;

import com.fooddelivery.food_delivery_backend.user.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}