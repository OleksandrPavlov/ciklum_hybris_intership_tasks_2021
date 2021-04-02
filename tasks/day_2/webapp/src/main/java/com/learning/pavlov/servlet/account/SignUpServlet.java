package com.learning.pavlov.servlet.account;

import com.learning.pavlov.models.User;
import com.learning.pavlov.services.AccountService;
import com.learning.pavlov.servlet.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.learning.pavlov.constants.AddressConstants.PRODUCT_LIST;
import static com.learning.pavlov.constants.AttributeConstants.CURRENT_USER;
import static com.learning.pavlov.constants.AttributeConstants.MESSAGE;
import static com.learning.pavlov.constants.ContextConstants.ACCOUNT_SERVICE;
import static com.learning.pavlov.constants.RequestParamConstants.LOGIN;
import static com.learning.pavlov.constants.RequestParamConstants.PASSWORD;
@WebServlet(urlPatterns="/sign_up")
public class SignUpServlet extends AbstractServlet {
    public static final String SIGN_UP_PAGE = "../view/authentication/sign_up.jsp";
    public static final String AUTH_FRAME_PAGE = "/WEB-INF/frame/auth_frame.jsp";
    public AccountService accountService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        accountService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(AUTH_FRAME_PAGE, SIGN_UP_PAGE, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter(LOGIN));
        user.setPassword(req.getParameter(PASSWORD));
        long userId = accountService.registerUser(user);
        if (userId > 0) {
            user.setId(userId);
            req.getSession().setAttribute(CURRENT_USER, user);
            resp.sendRedirect(PRODUCT_LIST);
        } else {
            req.setAttribute(MESSAGE, "Invalid login or password, try again!");
            forwardToPage(AUTH_FRAME_PAGE, SIGN_UP_PAGE, req, resp);
        }
    }
}
