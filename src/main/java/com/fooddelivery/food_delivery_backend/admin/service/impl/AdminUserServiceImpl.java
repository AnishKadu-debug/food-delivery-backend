package com.fooddelivery.food_delivery_backend.admin.service.impl;

import com.fooddelivery.food_delivery_backend.admin.service.AdminUserService;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.mapper.UserMapper;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse activateUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setActive(true);

        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse deactivateUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setActive(false);

        return UserMapper.toResponse(userRepository.save(user));
    }
}