package com.learning.pavlov.servlet.product;

import com.learning.pavlov.models.Product;
import com.learning.pavlov.services.ProductService;
import com.learning.pavlov.servlet.AbstractServlet;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.learning.pavlov.constants.AddressConstants.PRODUCT_LIST;
import static com.learning.pavlov.constants.AttributeConstants.*;
import static com.learning.pavlov.constants.ContextConstants.PRODUCT_SERVICE;
import static com.learning.pavlov.servlet.account.SignInServlet.AUTH_FRAME_PAGE;

@WebServlet(urlPatterns = "/create-product")
public class CreateNewProductServlet extends AbstractServlet {
    public static final String CREATE_PRODUCT_PAGE = "../view/product/create_product.jsp";
    private ProductService productService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        productService = (ProductService) context.getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(AUTH_FRAME_PAGE, CREATE_PRODUCT_PAGE, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        product.setName(req.getParameter(PRODUCT_NAME));
        product.setPrice(NumberUtils.createInteger(req.getParameter(PRODUCT_PRICE)));
        product.setProductStatus(extractProductStatus(req.getParameter(PRODUCT_STATUS)));
        int createdProductNumber = productService.addProduct(product);
        if (createdProductNumber > 0) {
            resp.sendRedirect(PRODUCT_LIST);
        } else {
            forwardToPage(AUTH_FRAME_PAGE, CREATE_PRODUCT_PAGE, req, resp);
        }
    }

    private static Product.ProductStatus extractProductStatus(final String stringRepresentation) {
        return Arrays.stream(Product.ProductStatus.values())
                .filter(el -> el.name().equalsIgnoreCase(stringRepresentation))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
