package com.epam.chernev.servlet.cart.command;

import com.epam.chernev.constants.CartConstants;
import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.SessionConstants;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.repository.Cart;
import com.epam.chernev.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class AddToCartCommand implements CartCommand {

    private final Logger log = Logger.getLogger(AddToCartCommand.class.getName());

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws RepositoryException {
        try {
            Long productId = Long.parseLong(request.getParameter(CartConstants.PRODUCT_ID_REQUEST_PARAMETER));
            log.info("Get session");
            HttpSession session = request.getSession();
            log.info("Get count of product from cart from session");
            Integer cartAmount = (Integer) session.getAttribute(SessionConstants.COUNT_CART_ATTRIBUTE);
            log.info("Increment count and write to session");
            session.setAttribute(SessionConstants.COUNT_CART_ATTRIBUTE, ++cartAmount);

            Cart cart = (Cart) session.getAttribute(SessionConstants.CART_ATTRIBUTE);
            ProductService productService = (ProductService) request.getServletContext().getAttribute(ContextConstants.PRODUCT_SERVICE);
            cart.addProduct(productService.getProductById(productId));

            log.info("Set content type");
            response.setContentType("text/plain");
            log.info("Writing response");
            OutputStream outStream = response.getOutputStream();
            outStream.write(cartAmount.toString().getBytes(StandardCharsets.UTF_8));
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

}
