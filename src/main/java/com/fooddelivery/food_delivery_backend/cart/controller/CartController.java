package com.fooddelivery.food_delivery_backend.cart.controller;

import com.fooddelivery.food_delivery_backend.cart.dto.AddCartItemRequest;
import com.fooddelivery.food_delivery_backend.cart.dto.CartResponse;
import com.fooddelivery.food_delivery_backend.cart.dto.UpdateCartItemRequest;
import com.fooddelivery.food_delivery_backend.cart.service.CartService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public ApiResponse<CartResponse> addItem(
            @Valid @RequestBody AddCartItemRequest request) {

        return ApiResponse.success(
                "Item added to cart successfully",
                cartService.addItem(request));
    }

    @GetMapping
    public ApiResponse<CartResponse> getCart() {

        return ApiResponse.success(
                "Cart fetched successfully",
                cartService.getMyCart());
    }

    @PutMapping("/items/{id}")
    public ApiResponse<CartResponse> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCartItemRequest request) {

        return ApiResponse.success(
                "Cart updated successfully",
                cartService.updateItem(id, request));
    }

    @DeleteMapping("/items/{id}")
    public ApiResponse<Void> removeItem(
            @PathVariable Long id) {

        cartService.removeItem(id);

        return ApiResponse.success(
                "Item removed successfully",
                null);
    }

    @DeleteMapping("/clear")
    public ApiResponse<Void> clearCart() {

        cartService.clearCart();

        return ApiResponse.success(
                "Cart cleared successfully",
                null);
    }
}