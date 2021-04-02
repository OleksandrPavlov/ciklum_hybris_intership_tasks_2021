package com.learning.pavlov.servlet.order;

import com.learning.pavlov.models.Order;
import com.learning.pavlov.models.User;
import com.learning.pavlov.services.OrderService;
import com.learning.pavlov.servlet.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.learning.pavlov.constants.AttributeConstants.CURRENT_ORDER;
import static com.learning.pavlov.constants.AttributeConstants.CURRENT_USER;
import static com.learning.pavlov.constants.ContextConstants.ORDER_SERVICE;

@WebServlet("/order/display")
public class DisplayOrderListServlet extends AbstractServlet {
    public static final String ORDER_PAGE = "../view/order/order_list.jsp";
    public static final String ORDER_PAGE_FRAME = "/WEB-INF/frame/cart-frame.jsp";
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        orderService = (OrderService) context.getAttribute(ORDER_SERVICE);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute(CURRENT_USER);
        Optional<Order> currentOrder = orderService.getCurrentOrder(currentUser.getId());
        req.setAttribute(CURRENT_ORDER, currentOrder.orElse(null));
        forwardToPage(ORDER_PAGE_FRAME, ORDER_PAGE, req, resp);
    }
}
