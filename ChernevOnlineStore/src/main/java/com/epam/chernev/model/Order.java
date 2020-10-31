package com.epam.chernev.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {

    private Long id;

    private OrderStatus status;

    private String statusDescription;

    private Timestamp date;

    private User user;

    private OrderedProducts orderedProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderedProducts getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(OrderedProducts orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderedProducts.ProductDTO orderedProduct : orderedProducts.getOrderedProducts()) {
            totalPrice = totalPrice.add(orderedProduct.getProductPrice().multiply(new BigDecimal(orderedProduct.getProductCount())));
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", statusDescription='" + statusDescription + '\'' +
                ", dateTime=" + date +
                ", user=" + user +
                ", orderedProducts=" + orderedProducts +
                '}';
    }
}
