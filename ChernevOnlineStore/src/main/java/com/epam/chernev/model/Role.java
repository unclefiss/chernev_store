package com.epam.chernev.model;

public enum Role {
    ADMIN, USER;

    public static Role getRole(Integer roleId) {
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

    public Integer getId() {
        for (int i = 0; i < Brand.values().length; i++) {
            if (Role.values()[i] == this) {
                return i;
            }
        }
        return -1;
    }
}
