package com.learning.pavlov.servlet.account;

import com.learning.pavlov.models.User;
import com.learning.pavlov.services.AccountService;
import com.learning.pavlov.servlet.AbstractServlet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.learning.pavlov.constants.AddressConstants.PRODUCT_LIST;
import static com.learning.pavlov.constants.AttributeConstants.CURRENT_USER;
import static com.learning.pavlov.constants.AttributeConstants.MESSAGE;
import static com.learning.pavlov.constants.ContextConstants.ACCOUNT_SERVICE;
import static com.learning.pavlov.constants.RequestParamConstants.LOGIN;
import static com.learning.pavlov.constants.RequestParamConstants.PASSWORD;

@Slf4j
@WebServlet(urlPatterns = "/sign_in")
public class SignInServlet extends AbstractServlet {
    public static final String SIGN_IN_PAGE = "../view/authentication/sign_in.jsp";
    public static final String AUTH_FRAME_PAGE = "/WEB-INF/frame/auth_frame.jsp";
    public AccountService accountService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        accountService = (AccountService) context.getAttribute(ACCOUNT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(AUTH_FRAME_PAGE, SIGN_IN_PAGE, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter(LOGIN));
        user.setPassword(req.getParameter(PASSWORD));
        Optional<User> currentUser = accountService.signUser(user);
        currentUser.ifPresent((u) -> {
            req.getSession().setAttribute(CURRENT_USER, u);
            try {
                resp.sendRedirect(PRODUCT_LIST);
            } catch (IOException exception) {
                log.error("An exception during sending redirect on /sign_in servlet!");
            }
        });
        if (!currentUser.isPresent()) {
            req.getSession().setAttribute(MESSAGE, "Invalid password or login!");
            forwardToPage(AUTH_FRAME_PAGE, SIGN_IN_PAGE, req, resp);
        }
    }
}
