package com.learning.pavlov.jdbc.handler;

import com.learning.pavlov.constants.DBConstants;
import com.learning.pavlov.models.Order;
import com.learning.pavlov.models.Product;
import com.learning.pavlov.models.User;
import org.apache.commons.dbutils.ResultSetHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HandlerFactory {
    private HandlerFactory() {

    }

    public static final String CUSTOM_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final ResultSetHandler<Optional<User>> USER_RESULT_SET_HANDLER = (rs) -> {
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt(DBConstants.ID));
            user.setLogin(rs.getString(DBConstants.LOGIN));
            user.setPassword(rs.getString(DBConstants.PASSWORD));
        }
        return Optional.ofNullable(user);
    };

    public static final ResultSetHandler<Optional<Product>> PRODUCT_RESULT_SET_HANDLER = (rs) -> {
        Product product = null;
        if (rs.next()) {
            product = new Product();
            product.setId(rs.getInt(DBConstants.ID));
            product.setName(rs.getString(DBConstants.NAME));
            product.setPrice(rs.getInt(DBConstants.PRICE));
            product.setCreatedAt(LocalDateTime.from(DateTimeFormatter.ofPattern(CUSTOM_TIME_PATTERN).parse(rs.getString(DBConstants.CREATED_AT))));
            product.setProductStatus(Product.ProductStatus.valueOf(rs.getString(DBConstants.PRODUCT_STATUS)));
        }
        return Optional.ofNullable(product);
    };

    public static final ResultSetHandler<List<Product>> PRODUCT_LIST_HANDLER = (rs) -> {
        List<Product> list = new ArrayList<>();
        Optional<Product> product;
        while ((product = PRODUCT_RESULT_SET_HANDLER.handle(rs)).isPresent()) {
            product.ifPresent(list::add);
        }
        return list;
    };

    public static final ResultSetHandler<Optional<Order>> ORDER_HANDLER = (rs) -> {
        Order order = null;
        if (rs.next()) {
            order = new Order();
            order.setId(rs.getLong(DBConstants.ORDER_ID));
            order.setCreatedAt(LocalDateTime.from(DateTimeFormatter.ofPattern(CUSTOM_TIME_PATTERN).parse(rs.getString(DBConstants.ORDER_CREATED))));
            Set<Order.OrderItem> orderItems = new HashSet<>();
            do {
                Order.OrderItem orderItem = new Order.OrderItem();
                Product product = new Product();
                product.setId(rs.getLong(DBConstants.PRODUCT_ID));
                product.setName(rs.getString(DBConstants.PRODUCT_NAME));
                product.setProductStatus(Product.ProductStatus.valueOf(rs.getString(DBConstants.PRODUCT_STATUS)));
                product.setCreatedAt(LocalDateTime.from(DateTimeFormatter.ofPattern(CUSTOM_TIME_PATTERN).parse(rs.getString(DBConstants.PRODUCT_CREATED))));
                product.setPrice(rs.getInt(DBConstants.PRODUCT_PRICE));
                orderItem.setProduct(product);
                orderItem.setQuantity(rs.getInt(DBConstants.PRODUCT_QUANTITY));
                orderItems.add(orderItem);
            } while (rs.next());
            order.setOrderItems(orderItems);
        }
        return Optional.ofNullable(order);
    };
}
