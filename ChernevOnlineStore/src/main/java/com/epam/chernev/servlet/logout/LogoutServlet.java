package com.epam.chernev.servlet.logout;

import com.epam.chernev.constants.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 8022829712206932464L;

    private final Logger log = Logger.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            log.info("Deleting user from session");
            HttpSession session = request.getSession();
            session.setAttribute("user", null);
            log.info("Forward to index");
            RequestDispatcher rq = request.getRequestDispatcher(Paths.INDEX_FORWARD_JSP);
            rq.forward(request, response);
        } catch (IOException | ServletException e) {
            log.warning(e.getMessage());
        }
    }

}
