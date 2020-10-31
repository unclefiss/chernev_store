package com.epam.chernev.servlet.cart.command;

import com.epam.chernev.constants.Paths;
import com.epam.chernev.constants.SessionConstants;
import com.epam.chernev.repository.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class ClearCartCommand implements CartCommand {

    private final Logger log = Logger.getLogger(ClearCartCommand.class.getName());

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Get session");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(SessionConstants.CART_ATTRIBUTE);
        cart.deleteAll();
        log.info("Increment count and write to session");
        session.setAttribute(SessionConstants.COUNT_CART_ATTRIBUTE, cart.getCount());
        log.info("Forward to cart");
        RequestDispatcher rd = request.getRequestDispatcher(Paths.CART_FORWARD_JSP);
        rd.forward(request, response);
    }
}
