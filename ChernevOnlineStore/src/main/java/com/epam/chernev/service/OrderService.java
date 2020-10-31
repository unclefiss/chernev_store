package com.epam.chernev.service;

import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.Order;
import com.epam.chernev.model.OrderedProducts;
import com.epam.chernev.repository.dao.OrderDAO;

import java.util.List;

public class OrderService {


    private final TransactionManager manager;

    private final OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
        manager = new TransactionManager();
    }

    public Long makeOrder(Order order) throws RepositoryException {
        return manager.doInTransaction(connection -> {
            orderDAO.addOrder(connection, order);
            Long id = orderDAO.getLastOrderId(connection);
            for (OrderedProducts.ProductDTO orderedProduct : order.getOrderedProducts().getOrderedProducts()) {
                orderDAO.addOrderedProduct(connection, orderedProduct, id);
            }
            return id;
        });
    }

    public Order getOrderById(Long id) throws RepositoryException {
        return manager.doInTransaction(connection -> orderDAO.getOrderById(connection, id));
    }

    public List<Order> getOrdersByUserId(Long userId) throws RepositoryException {
        return manager.doInTransaction(connection -> orderDAO.getOrdersByUserId(connection, userId));
    }

}
