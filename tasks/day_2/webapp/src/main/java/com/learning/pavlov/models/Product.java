package com.learning.pavlov.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
@Slf4j
@Data
@EqualsAndHashCode
public class Product {
    public static final String PRODUCT_NAME = "commands.product.name";
    public static final String PRODUCT_PRICE = "commands.product.price";
    public static final String PRODUCT_STATUS = "commands.product.status";
    public static final String PRODUCT_ID = "commands.product.id";
    private long id;
    private String name;
    private int price;
    private ProductStatus productStatus;
    private LocalDateTime createdAt;

    public enum ProductStatus {
        out_of_stock,
        in_stock,
        running_low
    }
}
