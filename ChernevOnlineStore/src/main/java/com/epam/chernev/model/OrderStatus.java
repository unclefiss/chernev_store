package com.epam.chernev.model;

public enum OrderStatus {
    ACCEPTED,
    CONFIRMED,
    FORMED,
    SENT,
    COMPLETED,
    CANCELED;

    public String getName() {
        return name().toLowerCase();
    }

    public Integer getId() {
        for (int i = 0; i < OrderStatus.values().length; i++) {
            if (OrderStatus.values()[i] == this) {
                return i;
            }
        }
        return -1;
    }

    public static OrderStatus getById(int id) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getId() == id) {
                return value;
            }
        }
        return null;
    }

}
