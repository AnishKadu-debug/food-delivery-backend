package com.fooddelivery.food_delivery_backend.order.repository;

import com.fooddelivery.food_delivery_backend.order.entity.Order;
import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(User customer);

    List<Order> findByRestaurant(Restaurant restaurant);

}