package com.learning.pavlov.listener;

import com.learning.pavlov.constants.ApplicationInitConstants;
import com.learning.pavlov.dao.OrderDao;
import com.learning.pavlov.dao.ProductDao;
import com.learning.pavlov.dao.impl.OrderDaoImpl;
import com.learning.pavlov.dao.impl.ProductDaoImpl;
import com.learning.pavlov.dao.impl.UserDaoImpl;
import com.learning.pavlov.jdbc.TransactionManager;
import com.learning.pavlov.jdbc.impl.TransactionManagerImpl;
import com.learning.pavlov.services.AccountService;
import com.learning.pavlov.services.OrderService;
import com.learning.pavlov.services.ProductService;
import com.learning.pavlov.services.impl.AccountServiceImpl;
import com.learning.pavlov.services.impl.OrderServiceImpl;
import com.learning.pavlov.services.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import static com.learning.pavlov.constants.ContextConstants.*;

@Slf4j
@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger CONTEXT_LISTENER_LOGGER
            = LoggerFactory.getLogger(AppContextListener.class);
    private Properties dbQueryProperties;
    private Properties localProperties;
    private BasicDataSource dataSource;
    private UserDaoImpl userDaoRepository;
    private AccountService userService;
    private TransactionManager transactionManager;
    private ProductService productService;
    private OrderService orderService;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        loadProperties();
        initBasicDataSource();
        initUserDaoRepository();
        initAccountService();
        initProductService();
        initOrderService();
        context.setAttribute(ACCOUNT_SERVICE, userService);
        context.setAttribute(PRODUCT_SERVICE, productService);
        context.setAttribute(ORDER_SERVICE, orderService);
        log.debug("Context has been initialized");
    }


    private void initAccountService() {
        transactionManager = new TransactionManagerImpl(dataSource);
        userService = new AccountServiceImpl(userDaoRepository, transactionManager);
        CONTEXT_LISTENER_LOGGER.debug("user service has been initialized");
    }

    private void initUserDaoRepository() {
        userDaoRepository = new UserDaoImpl(dbQueryProperties);
        CONTEXT_LISTENER_LOGGER.debug("User repository has been initialized");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            dataSource.close();
        } catch (SQLException e) {
            CONTEXT_LISTENER_LOGGER.error("IO exception was occured during closing dataSource!");
        }
    }

    private void initBasicDataSource() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(localProperties.getProperty(ApplicationInitConstants.DB_DRIVER));
        dataSource.setUrl(localProperties.getProperty(ApplicationInitConstants.DB_URL));
        dataSource.setUsername(localProperties.getProperty(ApplicationInitConstants.DB_USERNAME));
        dataSource.setPassword(localProperties.getProperty(ApplicationInitConstants.DB_PASSWORD));
        dataSource.setInitialSize(NumberUtils.createInteger(localProperties.getProperty(ApplicationInitConstants.DB_INIT_POOL_SIZE)));
        dataSource.setMaxTotal(NumberUtils.createInteger(localProperties.getProperty(ApplicationInitConstants.DB_MAX_POOL_SIZE)));
        CONTEXT_LISTENER_LOGGER.debug("Connection pool has been initialized!");
    }

    private void initProductService() {
        ProductDao productDao = new ProductDaoImpl(dbQueryProperties);
        productService = new ProductServiceImpl(transactionManager, productDao);
        log.debug("Product service has been initialized!");
    }


    private void initOrderService() {
        OrderDao orderDao = new OrderDaoImpl(dbQueryProperties);
        orderService = new OrderServiceImpl(orderDao, productService, transactionManager);
        log.debug("Order service has been initialized!");
    }

    private void loadProperties() {
        dbQueryProperties = loadProperties("sql_queries.properties");
        localProperties = loadProperties("local.properties");
    }

    private Properties loadProperties(String propertyFileName) {
        log.info(String.format("Loading %s...", propertyFileName));
        Properties properties = new Properties();
        ClassLoader classLoader = AppContextListener.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(propertyFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("The loading local" + propertyFileName + " has been fail!");
        }
        log.info(propertyFileName + " application init resources loaded !");
        return properties;
    }
}
