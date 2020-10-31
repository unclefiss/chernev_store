package com.epam.chernev.model;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Product {

    private Long id;

    private String title;

    private BigDecimal price;

    private Integer categoryId;

    private String categoryName;

    private Integer brandId;

    private String brandName;

    private String imagePath;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) &&
                Objects.equals(getTitle(), product.getTitle()) &&
                Objects.equals(getPrice(), product.getPrice()) &&
                Objects.equals(getCategoryId(), product.getCategoryId()) &&
                Objects.equals(getCategoryName(), product.getCategoryName()) &&
                Objects.equals(getBrandId(), product.getBrandId()) &&
                Objects.equals(getBrandName(), product.getBrandName()) &&
                Objects.equals(getImagePath(), product.getImagePath()) &&
                Objects.equals(getDescription(), product.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getPrice(), getCategoryId(), getCategoryName(), getBrandId(), getBrandName(), getImagePath(), getDescription());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
