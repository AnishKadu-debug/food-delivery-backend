package com.fooddelivery.food_delivery_backend.delivery.entity;

import com.fooddelivery.food_delivery_backend.common.audit.BaseAuditEntity;
import com.fooddelivery.food_delivery_backend.delivery.enums.DeliveryStatus;
import com.fooddelivery.food_delivery_backend.order.entity.Order;
import com.fooddelivery.food_delivery_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_partner_id", nullable = false)
    private User deliveryPartner;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private DeliveryStatus status = DeliveryStatus.ASSIGNED;

    private LocalDateTime assignedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime arrivedAt;

    private LocalDateTime pickedUpAt;

    private LocalDateTime deliveredAt;




}