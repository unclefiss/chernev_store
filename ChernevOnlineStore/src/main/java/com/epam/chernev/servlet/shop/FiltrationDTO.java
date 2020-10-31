package com.epam.chernev.servlet.shop;

import java.util.List;

public class FiltrationDTO {

    private String name;

    private List<String> categories;

    private String from;

    private String to;

    private List<String> brands;

    private String byPage;

    private String sortBy;

    private String page;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public String getByPage() {
        return byPage;
    }

    public void setByPage(String byPage) {
        this.byPage = byPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "FiltrationDTO{" +
                "name='" + name + '\'' +
                ", categories=" + categories +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", brands=" + brands +
                ", byPage='" + byPage + '\'' +
                ", sortBy='" + sortBy + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
