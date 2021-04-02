package com.learning.pavlov.servlet.product;

import com.learning.pavlov.models.Order;
import com.learning.pavlov.models.Product;
import com.learning.pavlov.models.User;
import com.learning.pavlov.services.OrderService;
import com.learning.pavlov.services.ProductService;
import com.learning.pavlov.servlet.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.learning.pavlov.constants.AttributeConstants.*;
import static com.learning.pavlov.constants.ContextConstants.ORDER_SERVICE;
import static com.learning.pavlov.constants.ContextConstants.PRODUCT_SERVICE;

@WebServlet("/product_list")
public class ProductListServlet extends AbstractServlet {
    public static final String PRODUCT_PAGE_FRAME = "/WEB-INF/frame/product_frame.jsp";
    public static final String PRODUCT_PAGE = "../view/product/product_list.jsp";

    private ProductService productService;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        productService = (ProductService) context.getAttribute(PRODUCT_SERVICE);
        orderService = (OrderService) context.getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.getAllProducts();
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER);
        req.setAttribute(CURRENT_PRODUCT_LIST, productList);
        Optional<Order> order = orderService.getCurrentOrder(currentUser.getId());
        if (!order.isPresent()) {
            req.setAttribute(CURRENT_ORDER, null);
        } else {
            req.setAttribute(CURRENT_ORDER, order.get());
        }
        forwardToPage(PRODUCT_PAGE_FRAME, PRODUCT_PAGE, req, resp);
    }
}
