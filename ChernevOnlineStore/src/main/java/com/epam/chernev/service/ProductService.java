package com.epam.chernev.service;

import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.Product;
import com.epam.chernev.repository.dao.ProductDAO;
import com.epam.chernev.servlet.shop.FiltrationDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final TransactionManager manager;

    private final ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
        manager = new TransactionManager();
    }


    public List<Product> getProducts() throws RepositoryException {
        return manager.doInTransaction(productDAO::getAllProducts);
    }

    public Product getProductById(Long id) throws RepositoryException {
        return manager.doInTransaction(connection -> productDAO.getProductById(connection, id));
    }

    public List<List<Product>> filtrateProducts(FiltrationDTO filtrationDTO) throws RepositoryException {
        return manager.doInTransaction(connection -> {
            List<Product> products = productDAO.filtrateProducts(connection, filtrationDTO);
            return splitPages(products, Integer.parseInt(filtrationDTO.getByPage()));
        });
    }


    private List<List<Product>> splitPages(List<Product> list, int byPage) {
        List<List<Product>> parts = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i += byPage) {
            parts.add(new ArrayList<>(
                    list.subList(i, Math.min(size, i + byPage)))
            );
        }
        return parts;
    }
}
