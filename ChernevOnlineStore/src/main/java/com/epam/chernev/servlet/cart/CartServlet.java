package com.epam.chernev.servlet.cart;

import com.epam.chernev.constants.CartConstants;
import com.epam.chernev.constants.Constants;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.servlet.cart.command.CommandContainer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 3914960361387300952L;

    private final Logger log = Logger.getLogger(CartServlet.class.getName());

    private CommandContainer container;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        container = new CommandContainer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType(Constants.RESPONSE_TYPE_TEXT);
        log.info("Start cart servlet");
        try {
            log.info("Getting command and delete processing ");
            container.get(req.getParameter(CartConstants.COMMAND_REQUEST_PARAMETER)).process(req, resp);

        } catch (NumberFormatException e) {
            log.warning("NumberFormatException " + e.getMessage());
        } catch (RepositoryException | ServletException | IOException e) {
            log.warning(e.getMessage());
        }
    }
}
