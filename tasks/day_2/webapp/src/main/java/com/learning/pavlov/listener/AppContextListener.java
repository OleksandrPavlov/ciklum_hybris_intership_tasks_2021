package com.learning.pavlov.listener;

import com.learning.pavlov.services.AccountService;
import com.learning.pavlov.services.OrderService;
import com.learning.pavlov.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.learning.pavlov.constants.ContextConstants.*;

@Slf4j
@WebListener
public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        OrderService orderService = (OrderService) appContext.getBean("orderService");
        ProductService productService = (ProductService) appContext.getBean("productService");
        AccountService accountService = (AccountService) appContext.getBean("accountService");
        context.setAttribute(ACCOUNT_SERVICE, accountService);
        context.setAttribute(PRODUCT_SERVICE, productService);
        context.setAttribute(ORDER_SERVICE, orderService);
        log.debug("Context has been initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
