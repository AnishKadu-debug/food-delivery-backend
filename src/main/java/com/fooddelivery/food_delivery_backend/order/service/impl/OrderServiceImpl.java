package com.fooddelivery.food_delivery_backend.order.service.impl;

import com.fooddelivery.food_delivery_backend.address.entity.Address;
import com.fooddelivery.food_delivery_backend.address.repository.AddressRepository;
import com.fooddelivery.food_delivery_backend.cart.entity.Cart;
import com.fooddelivery.food_delivery_backend.cart.entity.CartItem;
import com.fooddelivery.food_delivery_backend.cart.repository.CartRepository;
import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.order.dto.OrderResponse;
import com.fooddelivery.food_delivery_backend.order.dto.PlaceOrderRequest;
import com.fooddelivery.food_delivery_backend.order.dto.UpdateOrderStatusRequest;
import com.fooddelivery.food_delivery_backend.order.entity.Order;
import com.fooddelivery.food_delivery_backend.order.entity.OrderItem;
import com.fooddelivery.food_delivery_backend.order.enums.OrderStatus;
import com.fooddelivery.food_delivery_backend.order.mapper.OrderMapper;
import com.fooddelivery.food_delivery_backend.order.repository.OrderRepository;
import com.fooddelivery.food_delivery_backend.order.service.OrderService;
import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    private final CurrentUserService currentUserService;

    @Override
    public OrderResponse placeOrder(PlaceOrderRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Cart cart = cartRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getCartItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("This address does not belong to you");
        }

        Restaurant restaurant = cart.getCartItems()
                .getFirst()
                .getMenuItem()
                .getRestaurant();

        Order order = Order.builder()
                .customer(currentUser)
                .restaurant(restaurant)
                .deliveryAddress(address)
                .status(OrderStatus.PLACED)
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItems()) {

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menuItem(cartItem.getMenuItem())
                    .quantity(cartItem.getQuantity())
                    .price(cartItem.getPrice())
                    .build();

            order.getOrderItems().add(orderItem);

            total = total.add(
                    cartItem.getPrice().multiply(
                            BigDecimal.valueOf(cartItem.getQuantity())
                    )
            );
        }

        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);

        return OrderMapper.toResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getMyOrders() {

        User currentUser = currentUserService.getCurrentUser();

        return orderRepository.findByCustomer(currentUser)
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        User currentUser = currentUserService.getCurrentUser();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!order.getCustomer().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot view this order");
        }

        return OrderMapper.toResponse(order);
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId,
                                           UpdateOrderStatusRequest request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus(request.getStatus());

        return OrderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponse cancelOrder(Long orderId) {

        User currentUser = currentUserService.getCurrentUser();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!order.getCustomer().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You cannot cancel this order");
        }

        order.setStatus(OrderStatus.CANCELLED);

        return OrderMapper.toResponse(orderRepository.save(order));
    }
}