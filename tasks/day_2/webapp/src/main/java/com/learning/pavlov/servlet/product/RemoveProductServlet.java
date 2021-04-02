package com.learning.pavlov.servlet.product;

import com.learning.pavlov.services.ProductService;
import com.learning.pavlov.servlet.AbstractServlet;
import org.apache.commons.lang.math.NumberUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.learning.pavlov.constants.AddressConstants.PRODUCT_LIST;
import static com.learning.pavlov.constants.AttributeConstants.PRODUCT_ID;
import static com.learning.pavlov.constants.ContextConstants.PRODUCT_SERVICE;

@WebServlet(urlPatterns = "/product-remove")
public class RemoveProductServlet extends AbstractServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        productService = (ProductService) context.getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        productService.removeProduct(NumberUtils.createInteger(req.getParameter(PRODUCT_ID)));
        resp.sendRedirect(PRODUCT_LIST);
    }
}
