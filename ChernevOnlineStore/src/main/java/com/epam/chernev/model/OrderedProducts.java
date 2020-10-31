package com.epam.chernev.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderedProducts {

    private final List<ProductDTO> productDTOS;

    public OrderedProducts(Set<Map.Entry<Product, Integer>> products) {
        productDTOS = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : products) {
            productDTOS.add(new ProductDTO(entry.getKey(), entry.getValue()));
        }
    }

    public List<ProductDTO> getOrderedProducts() {
        return new ArrayList<>(productDTOS);
    }


    public static class ProductDTO {

        private Product product;

        private int productCount;

        private BigDecimal productPrice;

        public ProductDTO(Product product, int count) {
            this.product = product;
            this.productCount = count;
            this.productPrice = product.getPrice();
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public int getProductCount() {
            return productCount;
        }

        public void setProductCount(int productCount) {
            this.productCount = productCount;
        }

        public BigDecimal getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(BigDecimal productPrice) {
            this.productPrice = productPrice;
        }

        @Override
        public String toString() {
            return "ProductDTO{" +
                    "product=" + product +
                    ", productCount=" + productCount +
                    ", productPrice=" + productPrice +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderedProducts{" +
                "productDTOS=" + productDTOS +
                '}';
    }
}
