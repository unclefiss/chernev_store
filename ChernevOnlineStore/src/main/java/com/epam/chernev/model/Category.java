package com.epam.chernev.model;

public enum Category {
    SPORT_EQUIPMENT, CLOTHES;

    public static Category getCategory(Product product) {
        Integer roleId = product.getCategoryId();
        return Category.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase().replace("_", " ");
    }

    public Integer getId() {
        for (int i = 0; i < Category.values().length; i++) {
            if (Category.values()[i] == this) {
                return i;
            }
        }
        return -1;
    }

}
