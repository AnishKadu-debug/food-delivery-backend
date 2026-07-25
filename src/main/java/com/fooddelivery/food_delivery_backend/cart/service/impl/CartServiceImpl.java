package com.fooddelivery.food_delivery_backend.cart.service.impl;

import com.fooddelivery.food_delivery_backend.cart.dto.AddCartItemRequest;
import com.fooddelivery.food_delivery_backend.cart.dto.CartResponse;
import com.fooddelivery.food_delivery_backend.cart.dto.UpdateCartItemRequest;
import com.fooddelivery.food_delivery_backend.cart.entity.Cart;
import com.fooddelivery.food_delivery_backend.cart.entity.CartItem;
import com.fooddelivery.food_delivery_backend.cart.mapper.CartMapper;
import com.fooddelivery.food_delivery_backend.cart.repository.CartItemRepository;
import com.fooddelivery.food_delivery_backend.cart.repository.CartRepository;
import com.fooddelivery.food_delivery_backend.cart.service.CartService;
import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.menu.entity.MenuItem;
import com.fooddelivery.food_delivery_backend.menu.repository.MenuItemRepository;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final CurrentUserService currentUserService;

    @Override
    public CartResponse addItem(AddCartItemRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Cart cart = cartRepository.findByUserId(currentUser.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                            .user(currentUser)
                            .build();
                    return cartRepository.save(newCart);
                });

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));

        // Enforce single restaurant per cart
        if (!cart.getCartItems().isEmpty()) {

            Long existingRestaurantId = cart.getCartItems()
                    .getFirst()
                    .getMenuItem()
                    .getRestaurant()
                    .getId();

            if (!existingRestaurantId.equals(menuItem.getRestaurant().getId())) {
                throw new IllegalArgumentException(
                        "You can only add items from one restaurant at a time. Please clear your cart first."
                );
            }
        }

        CartItem cartItem = cartItemRepository
                .findByCartIdAndMenuItemId(cart.getId(), menuItem.getId())
                .orElse(null);

        if (cartItem == null) {

            cartItem = CartItem.builder()
                    .cart(cart)
                    .menuItem(menuItem)
                    .quantity(request.getQuantity())
                    .price(menuItem.getPrice())
                    .build();

            cart.getCartItems().add(cartItem);

        } else {

            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        }

        cartRepository.save(cart);

        return CartMapper.toResponse(cart);
    }

    @Override
    public CartResponse getMyCart() {

        User currentUser = currentUserService.getCurrentUser();

        Cart cart = cartRepository.findByUserId(currentUser.getId())
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .user(currentUser)
                                .build()
                ));

        return CartMapper.toResponse(cart);
    }

    @Override
    public CartResponse updateItem(Long cartItemId,
                                   UpdateCartItemRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getCart().getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot modify this cart");
        }

        cartItem.setQuantity(request.getQuantity());

        cartItemRepository.save(cartItem);

        return CartMapper.toResponse(cartItem.getCart());
    }

    @Override
    public void removeItem(Long cartItemId) {

        User currentUser = currentUserService.getCurrentUser();

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getCart().getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot modify this cart");
        }

        cartItemRepository.delete(cartItem);
    }

    @Override
    public void clearCart() {

        User currentUser = currentUserService.getCurrentUser();

        Cart cart = cartRepository.findByUserId(currentUser.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart not found"));

        cart.getCartItems().clear();

        cartRepository.save(cart);
    }
}