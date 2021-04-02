package com.learning.pavlov.filter;

import com.learning.pavlov.models.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.learning.pavlov.constants.AddressConstants.SIGN_IN_SERVLET;
import static com.learning.pavlov.constants.AddressConstants.SIGN_UP_SERVLET;
import static com.learning.pavlov.constants.AttributeConstants.CURRENT_USER;

@WebFilter(urlPatterns = "/*")
public class AuthorizingAccessFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if (!requestURI.contains(SIGN_IN_SERVLET) && !requestURI.contains(SIGN_UP_SERVLET) && !requestURI.contains("/static")) {
            User user = (User) request.getSession().getAttribute(CURRENT_USER);
            if (Objects.isNull(user)) {
                response.sendRedirect(SIGN_IN_SERVLET);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
