package com.epam.chernev.filter.access;

import com.epam.chernev.constants.Paths;
import com.epam.chernev.constants.SessionConstants;
import com.epam.chernev.model.User;

import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AccessControlFilter implements Filter {

    private final Logger log = Logger.getLogger(AccessControlFilter.class.getName());

    private Map<String, List<String>> constraints;

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Initialization");
        log.info("Get locale from filter config");
        String filepath = filterConfig.getInitParameter("filepath");
        InputStream xml = filterConfig.getServletContext().getResourceAsStream(filepath);
        constraints = DOMParser.getConstraints(xml);
        log.info(constraints.toString());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        for (Map.Entry<String, List<String>> entry : constraints.entrySet()) {
            if (path.matches(entry.getKey())) {
                log.info("Found path at xml file");
                if (session.getAttribute(SessionConstants.USER_ATTRIBUTE) != null) {
                    log.info("User is authorized");
                    User user = (User) session.getAttribute(SessionConstants.USER_ATTRIBUTE);
                    if (!entry.getValue().contains(user.getRole().getName())) {
                        log.info("User has not the access");
                        res.sendRedirect(Paths.ACCESS_ERROR_REDIRECT_JSP);
                    }
                } else {
                    log.info("Redirect to login");
                    req.getSession().setAttribute(SessionConstants.REFERER, req.getRequestURI());
                    res.sendRedirect(Paths.LOGIN_REDIRECT_SERVLET);
                }
            }
        }
        filterChain.doFilter(req, res);
    }
}
