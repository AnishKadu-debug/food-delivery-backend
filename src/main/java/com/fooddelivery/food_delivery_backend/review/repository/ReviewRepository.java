package com.fooddelivery.food_delivery_backend.review.repository;

import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;
import com.fooddelivery.food_delivery_backend.review.entity.Review;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRestaurant(Restaurant restaurant);

    Optional<Review> findByCustomerAndRestaurant(User customer,
                                                 Restaurant restaurant);

    boolean existsByCustomerAndRestaurant(User customer,
                                          Restaurant restaurant);
    @Query("""
       SELECT AVG(r.rating)
       FROM Review r
       WHERE r.restaurant = :restaurant
       """)
    Double findAverageRatingByRestaurant(@Param("restaurant") Restaurant restaurant);

    Long countByRestaurant(Restaurant restaurant);

}