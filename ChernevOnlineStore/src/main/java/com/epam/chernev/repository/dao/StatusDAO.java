package com.epam.chernev.repository.dao;

import com.epam.chernev.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StatusDAO {

    private final Logger log = Logger.getLogger(StatusDAO.class.getName());

    public List<Status> getCategories(Connection con){
        List<Status> statuses = new ArrayList<>();
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT categories.id, categories.name, categories.name_ru" +
                             " FROM chernevonlinestore.categories;")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    statuses.add(unmap(rs));
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return statuses;
    }

    public List<Status> getBrands(Connection con){
        List<Status> statuses = new ArrayList<>();
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT brands.id, brands.name, brands.name_ru" +
                             " FROM chernevonlinestore.brands;")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    statuses.add(unmap(rs));
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return statuses;
    }

    public List<Status> getOrderStatuses(Connection con){
        List<Status> statuses = new ArrayList<>();
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT order_statuses.id, order_statuses.name, order_statuses.name_ru" +
                             " FROM chernevonlinestore.order_statuses;")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    statuses.add(unmap(rs));
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return statuses;
    }

    private void map(PreparedStatement pst, Status status) throws SQLException {
        int k = 1;
        pst.setLong(k++, status.getId());
        pst.setString(k++, status.getName());
        pst.setString(k, status.getNameRu());
    }

    private Status unmap(ResultSet rs) throws SQLException {
        Status status = new Status();
        status.setId(rs.getLong("id"));
        status.setName(rs.getString("name"));
        status.setNameRu(rs.getString("name_ru"));
        return status;
    }

}
