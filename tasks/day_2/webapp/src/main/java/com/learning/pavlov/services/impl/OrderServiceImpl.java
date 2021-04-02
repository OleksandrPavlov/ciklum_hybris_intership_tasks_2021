package com.learning.pavlov.services.impl;

import com.learning.pavlov.dao.OrderDao;
import com.learning.pavlov.exceptions.ApplicationException;
import com.learning.pavlov.jdbc.TransactionManager;
import com.learning.pavlov.models.Order;
import com.learning.pavlov.models.Product;
import com.learning.pavlov.services.OrderService;
import com.learning.pavlov.services.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final OrderDao orderDao;
    private final TransactionManager transactionManager;

    public OrderServiceImpl(OrderDao orderDao, ProductService productService, TransactionManager transactionManager) {
        this.orderDao = orderDao;
        this.transactionManager = transactionManager;
        this.productService = productService;
    }

    @Override
    public Optional<Order> getCurrentOrder(long userId) {
        return transactionManager.executeInTransaction(() -> orderDao.getOrder(userId));
    }

    @Override
    public void updateOrderItemCount(long productId, int quantity, long userId) {
        Optional<Order> currentOrder = getCurrentOrder(userId);
        Optional<Product> currentProduct = productService.getProduct(productId);
        currentProduct.orElseThrow(() -> new ApplicationException("Invalid Product id, such product id is unknown for the system!"));

        if (currentOrder.isPresent()) {
            boolean isValidProductId = currentOrder.get().getOrderItems().stream().map(orderItem -> orderItem.getProduct().getId()).anyMatch(prId ->
                    prId.equals(productId)
            );
            if (!isValidProductId) {
                log.debug("Invalid productId has been introduced, check the front end side on incorrect id calculation!");
                throw new ApplicationException("Invalid productId has been introduced!");
            } else {
                transactionManager.executeInTransaction(() -> orderDao.updateOrderItemQuantity(productId, currentOrder.get().getId(), quantity));
            }
        } else {
            transactionManager.executeInTransaction(() -> orderDao.removeOrder(userId));
            long newlyCreatedOrderId = transactionManager.executeInTransaction(() -> orderDao.createNewOrder(userId));
            Order.OrderItem orderItem = new Order.OrderItem();
            Product product = new Product();
            product.setId(productId);
            orderItem.setQuantity(1);
            orderItem.setProduct(product);
            transactionManager.executeInTransaction(() -> orderDao.addOrderItem(orderItem, newlyCreatedOrderId));
        }
    }

    @Override
    public void addProductToOrderItem(long productId, int quantity, long userId) {
        Optional<Order> currentOrder = getCurrentOrder(userId);
        if (!currentOrder.isPresent()) {
            updateOrderItemCount(productId, 1, userId);
        } else {
            int currentProductQuantity = currentOrder.
                    get()
                    .getOrderItems()
                    .stream()
                    .filter(orderItem -> productId == orderItem.getProduct().getId())
                    .findAny()
                    .get()
                    .getQuantity();
            updateOrderItemCount(productId, currentProductQuantity + quantity, userId);
        }
    }


}
