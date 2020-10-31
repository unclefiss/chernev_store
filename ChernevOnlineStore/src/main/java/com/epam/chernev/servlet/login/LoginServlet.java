package com.epam.chernev.servlet.login;

import com.epam.chernev.constants.SessionConstants;
import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.Paths;
import com.epam.chernev.constants.Constants;
import com.epam.chernev.constants.RegistrationConstants;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.User;
import com.epam.chernev.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 5841707280508471240L;

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

    private UserService userService;

    @Override
    public void init(ServletConfig servletConfig) {
        log.info("Initialization");
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            log.warning(e.getMessage());
        }
        userService = (UserService) servletConfig.getServletContext().getAttribute(ContextConstants.USER_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType(Constants.RESPONSE_TYPE_TEXT);
            log.info("Start login");
            HttpSession session = request.getSession();
            String login = request.getParameter(RegistrationConstants.LOGIN);
            String password = request.getParameter(RegistrationConstants.PASSWORD);
            User user = userService.login(login, password);
            if (user != null) {
                log.info("Creating user attribute in database");
                session.setAttribute("user", user);
                String refererURI = (String) session.getAttribute(SessionConstants.REFERER);
                request.getSession().setAttribute(SessionConstants.REFERER, null);
                log.info("Redirect to success page");
                response.sendRedirect(refererURI);
            } else {
                log.info("Forward to login");
                RequestDispatcher rd = request.getRequestDispatcher(Paths.LOGIN_FORWARD_JSP);
                rd.forward(request, response);
            }
        } catch (NumberFormatException | RepositoryException | ServletException | IOException e) {
            log.warning(e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType(Constants.RESPONSE_TYPE_TEXT);
            log.info("Forward to login");
            HttpSession session = request.getSession();
            if (session.getAttribute(SessionConstants.REFERER) == null) {
                String refererURI = new URI(request.getHeader(SessionConstants.REFERER)).getPath();
                request.getSession().setAttribute(SessionConstants.REFERER, refererURI);
            }
            String refererURI = (String) session.getAttribute(SessionConstants.REFERER);
            log.warning(refererURI);
            RequestDispatcher rd = request.getRequestDispatcher(Paths.LOGIN_FORWARD_JSP);
            rd.forward(request, response);
        } catch (IOException | ServletException | URISyntaxException e) {
            log.warning(e.getMessage());
        }
    }

}
