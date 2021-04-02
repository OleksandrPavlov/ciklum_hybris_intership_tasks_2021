package com.learning.pavlov.services;

import com.learning.pavlov.models.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> getCurrentOrder(long userId);

    void updateOrderItemCount(long productId, int quantity, long userId);

    void addProductToOrderItem(long productId, int quantity, long userId);
}
