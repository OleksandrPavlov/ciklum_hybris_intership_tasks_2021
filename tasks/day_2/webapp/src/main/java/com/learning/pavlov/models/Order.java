package com.learning.pavlov.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Data
@EqualsAndHashCode
public class Order {
    public static final String ORDER_ID = "main.order.id";
    public static final String PRODUCT_TOTAL_PRICE = "main.order.product.total";
    public static final String PRODUCT_NAME = "main.order.product.name";
    public static final String PRODUCT_QUANTITY = "main.order.product.count";
    public static final String CREATED_DATE = "main.order.createAt";
    private long id;
    private Set<OrderItem> orderItems;
    private LocalDateTime createdAt;

    @Slf4j
    @Data
    @EqualsAndHashCode
    public static class OrderItem {
        private Product product;
        private int quantity;
    }
}
