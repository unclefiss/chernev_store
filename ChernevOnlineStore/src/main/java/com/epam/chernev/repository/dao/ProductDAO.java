package com.epam.chernev.repository.dao;

import com.epam.chernev.model.Product;
import com.epam.chernev.servlet.shop.FiltrationDTO;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductDAO {

    private final Logger log = Logger.getLogger(ProductDAO.class.getName());


    public List<Product> getAllProducts(Connection con) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT" +
                             " products.title, products.price, products.image_path, products.category_id, products.brand_id, products.description" +
                             " FROM chernevonlinestore.products;")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    products.add(unmap(rs));
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return products;
    }

    public List<Product> filtrateProducts(Connection con, FiltrationDTO filtrationDTO) {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pst =
                     con.prepareStatement(buildQuery(filtrationDTO))) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    products.add(unmap(rs));
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return products;
    }

    public Product getProductById(Connection con, Long id) {
        Product product = null;
        try (PreparedStatement pst = con.prepareStatement("SELECT products.id, products.title, products.price, products.image_path, products.category_id, products.brand_id, products.description " +
                "FROM chernevonlinestore.products " +
                "WHERE products.id = ?")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                product = unmap(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }


    private void map(PreparedStatement pst, Product product) throws SQLException {
        int k = 1;
        pst.setString(k++, product.getTitle());
        pst.setBigDecimal(k++, product.getPrice());
        pst.setString(k++, product.getImagePath());
        pst.setInt(k++, product.getCategoryId());
        pst.setInt(k++, product.getBrandId());
        pst.setString(k, product.getDescription());
    }

    private Product unmap(ResultSet rs) throws SQLException {
        Product product = new Product() {
        };
        product.setId(rs.getLong("id"));
        product.setTitle(rs.getString("title"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setImagePath(rs.getString("image_path"));
        product.setCategoryId(rs.getInt("category_id"));
        product.setBrandId(rs.getInt("brand_id"));
        product.setDescription(rs.getString("description"));
        return product;
    }

    private String buildQuery(FiltrationDTO filtrationDTO) {
        QueryBuilder query = new QueryBuilder();
        if (filtrationDTO.getName() != null && !filtrationDTO.getName().isEmpty()) {
            query.addTitle(filtrationDTO.getName());
        }
        if (filtrationDTO.getFrom() != null && !filtrationDTO.getFrom().isEmpty()) {
            query.addFrom(filtrationDTO.getFrom());
        }
        if (filtrationDTO.getTo() != null && !filtrationDTO.getTo().isEmpty()) {
            query.addTo(filtrationDTO.getTo());
        }
        if (!filtrationDTO.getCategories().isEmpty()) {
            query.addCategories(filtrationDTO.getCategories());
        }
        if (!filtrationDTO.getBrands().isEmpty()) {
            query.addBrand(filtrationDTO.getBrands());
        }
        log.warning(filtrationDTO.getSortBy());
        query.addSort(filtrationDTO.getSortBy());
        return query.build();
    }


    public static class QueryBuilder {

        private final StringBuilder query;

        ImmutableMap<Integer, String> sortingMap = new ImmutableMap.Builder<Integer, String>().put(0, "")
                .put(1, " ORDER BY products.title ASC")
                .put(2, " ORDER BY products.title DESC")
                .put(3, " ORDER BY products.price ASC")
                .put(4, " ORDER BY products.price DESC")
                .build();

        QueryBuilder() {
            query = new StringBuilder("SELECT products.id, products.title, products.price, products.image_path, products.category_id, products.brand_id, products.description " +
                    "FROM chernevonlinestore.products " +
                    "WHERE products.id = products.id ");
        }

        void addTitle(String title) {
            query.append(" AND LOCATE('").append(title).append("', products.title) <> 0");
        }

        void addFrom(String from) {
            query.append(" AND products.price >= ").append(Integer.parseInt(from));
        }

        void addTo(String to) {
            query.append(" AND products.price <= ").append(Integer.parseInt(to));
        }

        void addCategories(List<String> categories) {
            query.append(" AND (");
            for (String category : categories) {
                query.append(" products.category_id = ").append(Integer.parseInt(category));
                query.append(" OR");
            }
            query.delete(query.lastIndexOf("OR"), query.lastIndexOf("OR") + 2);
            query.append(")");
        }

        void addBrand(List<String> brands) {
            query.append(" AND (");
            for (String brand : brands) {
                query.append(" products.brand_id = ").append(Integer.parseInt(brand));
                query.append(" OR");
            }
            query.delete(query.lastIndexOf("OR"), query.lastIndexOf("OR") + 2);
            query.append(")");
        }

        void addSort(String sortBy) {
            query.append(sortingMap.get(Integer.parseInt(sortBy)));
        }

        String build() {
            query.append(";");
            return query.toString();
        }

    }
}
