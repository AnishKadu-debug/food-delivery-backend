package com.fooddelivery.food_delivery_backend.review.service.impl;

import com.fooddelivery.food_delivery_backend.common.exception.ForbiddenException;
import com.fooddelivery.food_delivery_backend.common.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery_backend.common.security.CurrentUserService;
import com.fooddelivery.food_delivery_backend.order.enums.OrderStatus;
import com.fooddelivery.food_delivery_backend.order.repository.OrderRepository;
import com.fooddelivery.food_delivery_backend.restaurant.entity.Restaurant;
import com.fooddelivery.food_delivery_backend.restaurant.repository.RestaurantRepository;
import com.fooddelivery.food_delivery_backend.review.dto.CreateReviewRequest;
import com.fooddelivery.food_delivery_backend.review.dto.ReviewResponse;
import com.fooddelivery.food_delivery_backend.review.dto.UpdateReviewRequest;
import com.fooddelivery.food_delivery_backend.review.entity.Review;
import com.fooddelivery.food_delivery_backend.review.mapper.ReviewMapper;
import com.fooddelivery.food_delivery_backend.review.repository.ReviewRepository;
import com.fooddelivery.food_delivery_backend.review.service.ReviewService;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final CurrentUserService currentUserService;

    @Override
    public ReviewResponse createReview(CreateReviewRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        if (reviewRepository.existsByCustomerAndRestaurant(currentUser, restaurant)) {
            throw new IllegalArgumentException("You have already reviewed this restaurant");
        }

        boolean hasOrdered = orderRepository.findByCustomer(currentUser)
                .stream()
                .anyMatch(order ->
                        order.getRestaurant().getId().equals(restaurant.getId())
                                && order.getStatus() == OrderStatus.DELIVERED);

        if (!hasOrdered) {
            throw new ForbiddenException(
                    "You can only review restaurants you have ordered from");
        }

        Review review = Review.builder()
                .customer(currentUser)
                .restaurant(restaurant)
                .rating(request.getRating())
                .comment(request.getComment())
                .build();

        Review saved = reviewRepository.save(review);

        updateRestaurantRating(restaurant);

        return ReviewMapper.toResponse(saved);
    }

    @Override
    public ReviewResponse updateReview(Long reviewId,
                                       UpdateReviewRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        if (!review.getCustomer().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You can update only your own review");
        }

        review.setRating(request.getRating());
        review.setComment(request.getComment());

        Review saved = reviewRepository.save(review);

        updateRestaurantRating(review.getRestaurant());

        return ReviewMapper.toResponse(saved);
    }

    @Override
    public void deleteReview(Long reviewId) {

        User currentUser = currentUserService.getCurrentUser();

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        if (!review.getCustomer().getId().equals(currentUser.getId())) {
            throw new ForbiddenException("You can delete only your own review");
        }

        Restaurant restaurant = review.getRestaurant();


        reviewRepository.delete(review);
        updateRestaurantRating(restaurant);
    }

    @Override
    public List<ReviewResponse> getRestaurantReviews(Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        return reviewRepository.findByRestaurant(restaurant)
                .stream()
                .map(ReviewMapper::toResponse)
                .toList();
    }

    private void updateRestaurantRating(Restaurant restaurant) {

        Double average = reviewRepository.findAverageRatingByRestaurant(restaurant);

        Long count = reviewRepository.countByRestaurant(restaurant);

        restaurant.setAverageRating(
                average == null ? 0.0 : average);

        restaurant.setTotalReviews(count.intValue());

        restaurantRepository.save(restaurant);
    }
}