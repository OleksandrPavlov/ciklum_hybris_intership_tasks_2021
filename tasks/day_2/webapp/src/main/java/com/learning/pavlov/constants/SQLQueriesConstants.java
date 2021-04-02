package com.learning.pavlov.constants;

public class SQLQueriesConstants {
    private SQLQueriesConstants() {

    }

    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?;";
    public static final String INSERT_INTO_USERS = "INSERT INTO users(login,password) VALUES(?,?);";
    public static final String GET_ORDER_BY_USER_ID = "SELECT o.id as order_id, o.user_id, o.status as order_status, o.created_at as order_created, pr.id as product_id, pr.name as product_name, pr.price as product_price, pr.products_status, pr.created_at as product_created, oi.quantity as product_quantity FROM orders as o INNER JOIN order_items as oi INNER JOIN products as pr WHERE o.id = oi.order_id AND pr.id = oi.product_id AND user_id = ?; ";
    public static final String GET_ALL_ORDERED_PRODUCTS = "SELECT * FROM products pr INNER JOIN order_items as oi WHERE pr.id = oi.product_id GROUP BY(oi.product_id) ORDER BY quantity;";
    public static final String GET_ALL_PRODUCTS = "SELECT * FROM products;";
    public static final String ADD_PRODUCT = "INSERT INTO products(name,price,products_status) VALUES(?,?,?);";
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?;";
    public static final String CREATE_NEW_ORDER = "INSERT INTO orders(user_id,status) VALUES(?,?);";
    public static final String ADD_ORDER_ITEM = "INSERT INTO order_items(order_id,product_id,quantity) VALUES(?,?,?);";
    public static final String UPDATE_ORDER_PRODUCT_QUANTITY = "UPDATE order_items SET quantity=? WHERE product_id = ? AND order_id = ?;";
    public static final String REMOVE_PRODUCT_BY_ID = "DELETE FROM products WHERE id=?;";
    public static final String DELETE_ALL_PRODUCTS = "DELETE FROM products;";
    public static final String REMOVE_ORDER = "DELETE FROM orders WHERE user_id = ?;";
}
