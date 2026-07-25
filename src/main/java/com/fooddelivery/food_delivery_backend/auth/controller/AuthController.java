package com.fooddelivery.food_delivery_backend.auth.controller;

import com.fooddelivery.food_delivery_backend.auth.service.AuthService;
import com.fooddelivery.food_delivery_backend.user.dto.AuthResponse;
import com.fooddelivery.food_delivery_backend.user.dto.LoginRequest;
import com.fooddelivery.food_delivery_backend.user.dto.RegisterRequest;
import com.fooddelivery.food_delivery_backend.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}