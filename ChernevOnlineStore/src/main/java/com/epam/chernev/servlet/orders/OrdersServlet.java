package com.epam.chernev.servlet.orders;

import com.epam.chernev.constants.Constants;
import com.epam.chernev.constants.ContextConstants;
import com.epam.chernev.constants.Paths;
import com.epam.chernev.constants.SessionConstants;
import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.Order;
import com.epam.chernev.model.OrderedProducts;
import com.epam.chernev.model.User;
import com.epam.chernev.service.OrderService;
import com.epam.chernev.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class OrdersServlet extends HttpServlet {

    private static final long serialVersionUID = -8756094480191512132L;

    private static final Logger log = Logger.getLogger(OrdersServlet.class.getName());

    private OrderService orderService;

    private ProductService productService;

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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        try {
            resp.setContentType(Constants.RESPONSE_TYPE_TEXT);
            User user = (User) session.getAttribute(SessionConstants.USER_ATTRIBUTE);
            List<Order> orders = orderService.getOrdersByUserId(user.getId());
            req.setAttribute("orders", orders);
            for (Order order : orders) {
                for (OrderedProducts.ProductDTO orderedProduct : order.getOrderedProducts().getOrderedProducts()) {
                    orderedProduct.setProduct(productService.getProductById(orderedProduct.getProduct().getId()));
                }
            }
            log.info("Forward to order page");
            RequestDispatcher rd = req.getRequestDispatcher(Paths.ORDERS_FORWARD_JSP);
            rd.forward(req, resp);
        } catch (RepositoryException | ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
