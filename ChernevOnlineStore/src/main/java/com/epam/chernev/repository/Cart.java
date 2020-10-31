package com.epam.chernev.repository;

import com.epam.chernev.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class Cart implements Serializable {

    private static final long serialVersionUID = -3383250486106529075L;

    private final transient Map<Product, Integer> cartMap;

    public Cart() {
        cartMap = new HashMap<>();
    }

    public int getProductCount(Product product) {
        ArrayList<Long> productsId = (ArrayList<Long>) cartMap.keySet().stream().map(Product::getId).collect(Collectors.toList());
        if (productsId.contains(product.getId())) {
            ArrayList<Product> currentProduct = (ArrayList<Product>) cartMap.keySet().stream().filter(pr -> pr.getId().equals(product.getId())).collect(Collectors.toList());
            return cartMap.get(currentProduct.get(0));
        } else {
            return 0;
        }
    }

    public void addProduct(Product product) {
        ArrayList<Long> productsId = (ArrayList<Long>) cartMap.keySet().stream().map(Product::getId).collect(Collectors.toList());
        if (productsId.contains(product.getId())) {
            ArrayList<Product> currentProduct = (ArrayList<Product>) cartMap.keySet().stream().filter(pr -> pr.getId().equals(product.getId())).collect(Collectors.toList());
            int count = cartMap.get(currentProduct.get(0));
            cartMap.put(currentProduct.get(0), ++count);
        } else {
            cartMap.put(product, 1);
        }
    }

    public void addProduct(Product product, int count) {
        ArrayList<Long> productsId = (ArrayList<Long>) cartMap.keySet().stream().map(Product::getId).collect(Collectors.toList());
        if (productsId.contains(product.getId())) {
            int oldCount = cartMap.get(product);
            cartMap.put(product, oldCount + count);
        } else {
            cartMap.put(product, count);
        }
    }

    public void deleteProduct(Product product) {
        ArrayList<Product> currentProduct = (ArrayList<Product>) cartMap.keySet().stream().filter(pr -> pr.getId().equals(product.getId())).collect(Collectors.toList());
        cartMap.remove(currentProduct.get(0));
    }

    public void deleteOneProduct(Product product) {
        ArrayList<Long> productsId = (ArrayList<Long>) cartMap.keySet().stream().map(Product::getId).collect(Collectors.toList());
        if (productsId.contains(product.getId())) {
            ArrayList<Product> currentProduct = (ArrayList<Product>) cartMap.keySet().stream().filter(pr -> pr.getId().equals(product.getId())).collect(Collectors.toList());
            int count = cartMap.get(currentProduct.get(0));
            if (count > 0) {
                cartMap.put(currentProduct.get(0), --count);
            }
            if(cartMap.get(currentProduct.get(0)) == 0){
                deleteProduct(currentProduct.get(0));
            }
        }
    }

    public void setCountOfProduct(Product product, int count){
        ArrayList<Long> productsId = (ArrayList<Long>) cartMap.keySet().stream().map(Product::getId).collect(Collectors.toList());
        if (productsId.contains(product.getId())) {
            ArrayList<Product> currentProduct = (ArrayList<Product>) cartMap.keySet().stream().filter(pr -> pr.getId().equals(product.getId())).collect(Collectors.toList());
            if (count >= 0) {
                cartMap.put(currentProduct.get(0), count);
            }
            if(cartMap.get(currentProduct.get(0)) == 0){
                deleteProduct(currentProduct.get(0));
            }
        }
    }


    public void deleteAll() {
        cartMap.clear();
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            totalPrice = totalPrice.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
        }
        return totalPrice;
    }

    public int getCount() {
        int count = 0;
        for (Integer value : cartMap.values()) {
            count += value;
        }
        return count;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(cartMap.keySet());
    }

    public Set<Map.Entry<Product, Integer>> getEntry() {
        return new HashMap<>(cartMap).entrySet();
    }

}
