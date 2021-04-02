package com.learning.pavlov.dao.impl;

import com.learning.pavlov.constants.SQLQueriesConstants;
import com.learning.pavlov.dao.OrderDao;
import com.learning.pavlov.models.Order;
import com.learning.pavlov.util.ThreadLocalConnection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static com.learning.pavlov.jdbc.handler.HandlerFactory.ORDER_HANDLER;
import static com.learning.pavlov.jdbc.handler.JDBCUtil.putErrorMsgToConnection;

public class OrderDaoImpl implements OrderDao {

    private final QueryRunner queryRunner;

    public OrderDaoImpl() {
        queryRunner = new QueryRunner();
    }

    @Override
    public int updateOrderItemQuantity(long productId, long orderId, int quantity) {
        Connection connection = ThreadLocalConnection.getConnection();
        try {
            return queryRunner.update(connection, SQLQueriesConstants.UPDATE_ORDER_PRODUCT_QUANTITY, quantity, productId, orderId);
        } catch (SQLException ex) {
            putErrorMsgToConnection(ex.getMessage(), connection);
        }
        return 0;
    }

    @Override
    public Optional<Order> getOrder(long userId) {
        Connection connection = ThreadLocalConnection.getConnection();
        try {
            return queryRunner.query(connection, SQLQueriesConstants.GET_ORDER_BY_USER_ID, ORDER_HANDLER, userId);
        } catch (SQLException ex) {
            putErrorMsgToConnection(ex.getMessage(), connection);
        }
        return Optional.empty();
    }

    @Override
    public int addOrderItem(Order.OrderItem orderItem, long orderId) {
        Connection connection = ThreadLocalConnection.getConnection();
        try {
            return queryRunner.update(connection, SQLQueriesConstants.ADD_ORDER_ITEM, orderId, orderItem.getProduct().getId(), orderItem.getQuantity());
        } catch (SQLException ex) {
            putErrorMsgToConnection(ex.getMessage(), connection);
        }
        return 0;
    }

    @Override
    public long createNewOrder(long userId) {
        Connection connection = ThreadLocalConnection.getConnection();
        try {
            return queryRunner.insert(connection, SQLQueriesConstants.CREATE_NEW_ORDER, new ScalarHandler<BigInteger>(), userId, "newly").longValue();
        } catch (SQLException ex) {
            putErrorMsgToConnection(ex.getMessage(), connection);
        }
        return -1;
    }

    @Override
    public boolean removeOrder(long userId) {
        Connection connection = ThreadLocalConnection.getConnection();
        try {
            return queryRunner.update(connection, SQLQueriesConstants.REMOVE_ORDER, userId) > 0;
        } catch (SQLException ex) {
            putErrorMsgToConnection(ex.getMessage(), connection);
        }
        return Boolean.FALSE;
    }
}