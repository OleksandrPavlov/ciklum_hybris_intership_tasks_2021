package com.learning.pavlov.servlet.order;

import com.learning.pavlov.models.User;
import com.learning.pavlov.services.OrderService;
import com.learning.pavlov.servlet.AbstractServlet;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.learning.pavlov.constants.AttributeConstants.*;
import static com.learning.pavlov.constants.ContextConstants.ORDER_SERVICE;

@WebServlet(urlPatterns = "/order/update_quantity")
public class UpdateProductQuantityServlet extends AbstractServlet {
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        orderService = (OrderService) context.getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(CURRENT_USER);
        int productId = NumberUtils.createInteger(req.getParameter(PRODUCT_ID));
        int quantity = NumberUtils.createInteger(req.getParameter(PRODUCT_COUNT));
        orderService.updateOrderItemCount(productId, quantity, user.getId());
    }
}
