package com.fooddelivery.food_delivery_backend.auth.controller;

import com.fooddelivery.food_delivery_backend.auth.service.AuthService;
import com.fooddelivery.food_delivery_backend.user.dto.RegisterRequest;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {

        return authService.register(request);

    }

}