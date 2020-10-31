package com.epam.chernev.servlet.cart.command;

import com.epam.chernev.constants.CartConstants;
import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.Paths;
import com.epam.chernev.constants.SessionConstants;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.Product;
import com.epam.chernev.repository.Cart;
import com.epam.chernev.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class DeleteProductCommand implements CartCommand {

    private final Logger log = Logger.getLogger(DeleteProductCommand.class.getName());

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws RepositoryException, ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter(CartConstants.PRODUCT_ID_REQUEST_PARAMETER));
        log.info("Get session");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(SessionConstants.CART_ATTRIBUTE);
        ProductService productService = (ProductService) request.getServletContext().getAttribute(ContextConstants.PRODUCT_SERVICE);
        Product product = productService.getProductById(productId);
        cart.deleteProduct(product);
        log.info("Increment count and write to session");
        session.setAttribute(SessionConstants.COUNT_CART_ATTRIBUTE, cart.getCount());
        log.info("Forward to cart");
        RequestDispatcher rd = request.getRequestDispatcher(Paths.CART_FORWARD_JSP);
        rd.forward(request, response);
    }
}
