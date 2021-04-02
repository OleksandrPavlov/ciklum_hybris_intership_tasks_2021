package com.learning.pavlov.servlet.product;

import com.learning.pavlov.services.ProductService;
import com.learning.pavlov.servlet.AbstractServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.learning.pavlov.constants.AddressConstants.PRODUCT_LIST;
import static com.learning.pavlov.constants.ContextConstants.PRODUCT_SERVICE;

@WebServlet(urlPatterns = "/remove-all-products")
public class RemoveAllProductsServlet extends AbstractServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        productService = (ProductService) context.getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int removedProductsNumber = productService.removeAllProducts();
        resp.sendRedirect(req.getContextPath() + PRODUCT_LIST);
    }
}
