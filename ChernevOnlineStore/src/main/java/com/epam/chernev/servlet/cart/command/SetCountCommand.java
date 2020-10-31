package com.epam.chernev.servlet.cart.command;

import com.epam.chernev.constants.CartConstants;
import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.SessionConstants;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.Product;
import com.epam.chernev.repository.Cart;
import com.epam.chernev.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Logger;

public class SetCountCommand implements CartCommand {

    private final Logger log = Logger.getLogger(SetCountCommand.class.getName());

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws RepositoryException {
        Long productId = Long.parseLong(request.getParameter(CartConstants.PRODUCT_ID_REQUEST_PARAMETER));
        log.info("Get count from request");
        int count = Integer.parseInt(request.getParameter(CartConstants.COUNT_REQUEST_PARAMETER));
        log.info("Get session");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(SessionConstants.CART_ATTRIBUTE);
        ProductService productService = (ProductService) request.getServletContext().getAttribute(ContextConstants.PRODUCT_SERVICE);
        Product product = productService.getProductById(productId);
        cart.setCountOfProduct(product, count);
        log.info("Increment count and write to session");
        session.setAttribute(SessionConstants.COUNT_CART_ATTRIBUTE, cart.getCount());
        BigDecimal totalPrice = product.getPrice().multiply(new BigDecimal(cart.getProductCount(product)));
        try {
            log.info("Set content type");
            response.setContentType("text/plain");
            log.info("Writing response");
            PrintWriter writer = response.getWriter();
            writer.print(totalPrice.toString() + " " + cart.getCount());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }
}
