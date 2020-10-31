package com.epam.chernev.servlet.order;

import com.epam.chernev.constants.OrderConstants;
import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.Constants;
import com.epam.chernev.constants.Paths;
import com.epam.chernev.constants.SessionConstants;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.User;
import com.epam.chernev.model.Order;
import com.epam.chernev.model.OrderedProducts;
import com.epam.chernev.model.OrderStatus;
import com.epam.chernev.repository.Cart;
import com.epam.chernev.service.OrderService;
import com.epam.chernev.service.ProductService;
import com.epam.chernev.service.StatusService;
import com.epam.chernev.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = -8756094480191512132L;

    private static final Logger log = Logger.getLogger(OrderServlet.class.getName());

    private OrderService orderService;

    private ProductService productService;

    private UserService userService;

    private StatusService statusService;

    @Override
    public void init(ServletConfig servletConfig) {
        log.info("Initialization");
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            log.warning(e.getMessage());
        }
        orderService = (OrderService) servletConfig.getServletContext().getAttribute(ContextConstants.ORDER_SERVICE);
        productService = (ProductService) servletConfig.getServletContext().getAttribute(ContextConstants.PRODUCT_SERVICE);
        userService = (UserService) servletConfig.getServletContext().getAttribute(ContextConstants.USER_SERVICE);
        statusService = (StatusService) servletConfig.getServletContext().getAttribute(ContextConstants.STATUS_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType(Constants.RESPONSE_TYPE_TEXT);
            HttpSession session = req.getSession();
            Cart cart = (Cart) session.getAttribute(SessionConstants.CART_ATTRIBUTE);
            Order order = new Order();
            order.setStatus(OrderStatus.ACCEPTED);
            order.setDate(new Timestamp(System.currentTimeMillis()));
            User user = (User) session.getAttribute(SessionConstants.USER_ATTRIBUTE);
            order.setUser(user);
            order.setOrderedProducts(new OrderedProducts(cart.getEntry()));
            order.setStatusDescription("");
            cart.deleteAll();
            session.setAttribute(SessionConstants.COUNT_CART_ATTRIBUTE, 0);
            Long orderId = orderService.makeOrder(order);
            log.info("Redirect to success page");
            resp.sendRedirect(Paths.ORDER_REDIRECT_SERVLET + "?" + OrderConstants.ORDER_ID_REQUEST_PARAMETER + "=" + orderId.toString());
        } catch (RepositoryException | IOException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            if (req.getParameterValues(OrderConstants.ORDER_ID_REQUEST_PARAMETER) != null) {

                resp.setContentType(Constants.RESPONSE_TYPE_TEXT);
                Long orderId = Long.parseLong(req.getParameter(OrderConstants.ORDER_ID_REQUEST_PARAMETER));
                Order order = orderService.getOrderById(orderId);
                order.setUser(userService.getUserById(order.getUser().getId()));
                req.setAttribute(OrderConstants.DATE_REQUEST_ATTRIBUTE, order.getDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
                for (OrderedProducts.ProductDTO orderedProduct : order.getOrderedProducts().getOrderedProducts()) {
                    orderedProduct.setProduct(productService.getProductById(orderedProduct.getProduct().getId()));
                }
                req.setAttribute(OrderConstants.ORDER_REQUEST_ATTRIBUTE, order);
                req.setAttribute(OrderConstants.ORDER_STATUSES_REQUEST_ATTRIBUTE, statusService.getOrderStatuses());
                log.info("Forward to order page");
                RequestDispatcher rd = req.getRequestDispatcher(Paths.ORDER_FORWARD_JSP);
                rd.forward(req, resp);
            } else {
                log.info("Redirect to success page");
                resp.sendRedirect(Paths.ORDERS_REDIRECT_SERVLET);
            }
        } catch (RepositoryException | NumberFormatException | IOException | ServletException e) {
            log.warning(e.getMessage());
        }
    }
}
