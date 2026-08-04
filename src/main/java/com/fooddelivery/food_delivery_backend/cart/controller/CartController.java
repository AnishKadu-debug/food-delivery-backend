package com.fooddelivery.food_delivery_backend.cart.controller;

import com.fooddelivery.food_delivery_backend.cart.dto.AddCartItemRequest;
import com.fooddelivery.food_delivery_backend.cart.dto.CartResponse;
import com.fooddelivery.food_delivery_backend.cart.dto.UpdateCartItemRequest;
import com.fooddelivery.food_delivery_backend.cart.service.CartService;
import com.fooddelivery.food_delivery_backend.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(
        name = "Cart",
        description = "APIs for managing the authenticated customer's shopping cart."
)
public class CartController {

    private final CartService cartService;

    @Operation(
            summary = "Add item to cart",
            description = "Adds a menu item to the authenticated customer's shopping cart."
    )
    @PostMapping("/items")
    public ApiResponse<CartResponse> addItem(
            @Valid @RequestBody AddCartItemRequest request) {

        return ApiResponse.success(
                "Item added to cart successfully",
                cartService.addItem(request)
        );
    }

    @Operation(
            summary = "Get current cart",
            description = "Returns the authenticated customer's current shopping cart."
    )
    @GetMapping
    public ApiResponse<CartResponse> getCart() {

        return ApiResponse.success(
                "Cart fetched successfully",
                cartService.getMyCart()
        );
    }

    @Operation(
            summary = "Update cart item",
            description = "Updates the quantity of an existing item in the authenticated customer's cart."
    )
    @PutMapping("/items/{id}")
    public ApiResponse<CartResponse> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCartItemRequest request) {

        return ApiResponse.success(
                "Cart updated successfully",
                cartService.updateItem(id, request)
        );
    }

    @Operation(
            summary = "Remove item from cart",
            description = "Removes a specific item from the authenticated customer's shopping cart."
    )
    @DeleteMapping("/items/{id}")
    public ApiResponse<Void> removeItem(
            @PathVariable Long id) {

        cartService.removeItem(id);

        return ApiResponse.success(
                "Item removed successfully",
                null
        );
    }

    @Operation(
            summary = "Clear cart",
            description = "Removes all items from the authenticated customer's shopping cart."
    )
    @DeleteMapping("/clear")
    public ApiResponse<Void> clearCart() {

        cartService.clearCart();

        return ApiResponse.success(
                "Cart cleared successfully",
                null
        );
    }
}