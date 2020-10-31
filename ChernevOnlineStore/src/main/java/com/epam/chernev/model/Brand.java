package com.epam.chernev.model;

public enum Brand {
    ADIDAS, PUMA, NEW_BALANCE;

    public static Brand getCategory(Product product) {
        Integer roleId = product.getBrandId();
        return Brand.values()[roleId];
    }

    public String getName() {
        return name().replace("_", " ");
    }

    public Integer getId() {
        for (int i = 0; i < Brand.values().length; i++) {
            if (Brand.values()[i] == this) {
                return i;
            }
        }
        return -1;
    }

}
