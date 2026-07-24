package com.fooddelivery.food_delivery_backend.auth.service.impl;

import com.fooddelivery.food_delivery_backend.auth.service.AuthService;
import com.fooddelivery.food_delivery_backend.common.exception.DuplicateResourceException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.exception.UnauthorizedException;
import com.fooddelivery.food_delivery_backend.common.mapper.UserMapper;
import com.fooddelivery.food_delivery_backend.security.jwt.JwtService;
import com.fooddelivery.food_delivery_backend.user.dto.AuthResponse;
import com.fooddelivery.food_delivery_backend.user.dto.LoginRequest;
import com.fooddelivery.food_delivery_backend.user.dto.RegisterRequest;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import com.fooddelivery.food_delivery_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserResponse register(RegisterRequest request) {

        if (userService.existsByEmail(request.getEmail()))
            throw new DuplicateResourceException("Email already exists");

        if (userService.existsByPhone(request.getPhone()))
            throw new DuplicateResourceException("Phone already exists");

        User user = UserMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userService.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

        } catch (Exception e) {

            throw new UnauthorizedException(
                    "Invalid email or password");
        }


        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        String jwt = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwt)
                .user(UserMapper.toResponse(user))
                .build();
    }
}