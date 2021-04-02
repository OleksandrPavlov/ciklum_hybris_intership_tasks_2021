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

import static com.learning.pavlov.constants.AddressConstants.PRODUCT_LIST;
import static com.learning.pavlov.constants.AttributeConstants.CURRENT_USER;
import static com.learning.pavlov.constants.AttributeConstants.PRODUCT_ID;
import static com.learning.pavlov.constants.ContextConstants.ORDER_SERVICE;

@WebServlet(urlPatterns = "/order/add_product")
public class AddProductToOrderServlet extends AbstractServlet {
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        orderService = (OrderService) context.getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Helloo");
        User user = (User) req.getSession().getAttribute(CURRENT_USER);
        int productId = NumberUtils.createInteger(req.getParameter(PRODUCT_ID));
        orderService.addProductToOrderItem(productId,1, user.getId());
        resp.sendRedirect(PRODUCT_LIST);
    }
}
