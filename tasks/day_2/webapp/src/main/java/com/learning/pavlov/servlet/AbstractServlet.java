package com.learning.pavlov.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.learning.pavlov.constants.AttributeConstants.INNER_PAGE;

public class AbstractServlet extends HttpServlet {
    protected void forwardToPage(String outerPage, String innerPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(INNER_PAGE, innerPage);
        req.getRequestDispatcher(req.getContextPath() + outerPage).forward(req, resp);
    }
}
