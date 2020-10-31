package com.epam.chernev.repository.dao;

import com.epam.chernev.model.Order;
import com.epam.chernev.model.Product;
import com.epam.chernev.model.OrderedProducts;
import com.epam.chernev.model.OrderStatus;
import com.epam.chernev.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.logging.Logger;

public class OrderDAO {


    private final Logger log = Logger.getLogger(OrderDAO.class.getName());


    public void addOrder(Connection con, Order order) {
        try (PreparedStatement pst =
                     con.prepareStatement("INSERT INTO orders (status_id, description, user_id, date)" +
                             " VALUES (?, ?, ?, ?)")) {
            map(pst, order);
            int res = pst.executeUpdate();
            if (res != 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
    }

    public Long getLastOrderId(Connection con) {
        Long id = null;
        try (ResultSet rs = con.createStatement().executeQuery("SELECT orders.id FROM chernevonlinestore.orders ")) {
            while (rs.next()) {
                id = rs.getLong("id");
            }
        } catch (SQLException throwables) {
            log.warning(throwables.getMessage());
        }
        return id;
    }

    public Order getOrderById(Connection con, Long id) {
        Order order = null;
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT orders.id, orders.status_id, orders.description, orders.date, orders.user_id" +
                             " FROM chernevonlinestore.orders WHERE orders.id = ?")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                order = unmap(rs);
                order.setOrderedProducts(new OrderedProducts(this.getOrderedProductByOrderId(con, id)));
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return order;
    }

    public List<Order> getOrdersByUserId(Connection con, Long userId) {
        List<Order> orders = null;
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT orders.id, orders.status_id, orders.description, orders.date, orders.user_id" +
                             " FROM chernevonlinestore.orders WHERE orders.user_id = ?")) {
            pst.setLong(1, userId);
            try (ResultSet rs = pst.executeQuery()) {
                orders = new ArrayList<>();
                while (rs.next()) {
                    Order order = unmap(rs);
                    order.setOrderedProducts(new OrderedProducts(this.getOrderedProductByOrderId(con, order.getId())));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return orders;
    }

    public Set<Map.Entry<Product, Integer>> getOrderedProductByOrderId(Connection con, Long orderId) {
        Map<Product, Integer> products = null;
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT ordered_products.product_id, ordered_products.price, ordered_products.count" +
                             " FROM chernevonlinestore.ordered_products WHERE order_id = ?")) {
            pst.setLong(1, orderId);
            ResultSet rs = pst.executeQuery();
            products = unmapProductDTO(rs);
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        assert products != null;
        return products.entrySet();
    }

    public void addOrderedProduct(Connection con, OrderedProducts.ProductDTO productDTO, Long orderId) {
        try (PreparedStatement pst =
                     con.prepareStatement("INSERT INTO ordered_products (product_id, price, count, order_id)" +
                             " VALUES (?, ?, ?, ?)")) {
            mapProductDTO(pst, productDTO, orderId);
            int res = pst.executeUpdate();
            if (res != 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
    }

    private void map(PreparedStatement pst, Order order) throws SQLException {
        int k = 1;
        pst.setInt(k++, order.getStatus().getId());
        pst.setString(k++, order.getStatusDescription());
        pst.setLong(k++, order.getUser().getId());
        pst.setTimestamp(k, order.getDate());
    }

    private Order unmap(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setStatus(OrderStatus.getById(rs.getInt("status_id")));
        order.setStatusDescription(rs.getString("description"));
        order.setDate(rs.getTimestamp("date"));
        User user = new User();
        user.setId(rs.getLong("user_id"));
        order.setUser(user);
        return order;
    }

    private void mapProductDTO(PreparedStatement pst, OrderedProducts.ProductDTO productDTO, Long orderId) throws SQLException {
        int k = 1;
        pst.setLong(k++, productDTO.getProduct().getId());
        pst.setBigDecimal(k++, productDTO.getProductPrice());
        pst.setInt(k++, productDTO.getProductCount());
        pst.setLong(k, orderId);
    }

    private Map<Product, Integer> unmapProductDTO(ResultSet rs) throws SQLException {
        Map<Product, Integer> products = new HashMap<>();
        while (rs.next()) {
            Product product = new Product() {
            };
            product.setId(rs.getLong("product_id"));
            product.setPrice(rs.getBigDecimal("price"));
            int count = rs.getInt("count");
            products.put(product, count);
        }
        return products;
    }

}
