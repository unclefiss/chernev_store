package com.epam.chernev.repository.dao;

import com.epam.chernev.model.Role;
import com.epam.chernev.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDAO {

    private final Logger log = Logger.getLogger(UserDAO.class.getName());

    public void create(Connection con, User user) {
        try (PreparedStatement pst =
                     con.prepareStatement("INSERT INTO users (f_name,l_name, login, email, phone, password, user_pic_path, role_id)" +
                             " VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            map(pst, user);
            int res = pst.executeUpdate();
            if (res != 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
    }

    public User getByLogin(Connection con, String login) {
        User user = null;
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT * FROM chernevonlinestore.users WHERE login= ?")) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                user = unmap(rs);
                int res = pst.executeUpdate();
                if (res != 0) {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return user;
    }

    public User getById(Connection con, Long id) {
        User user = null;
        try (PreparedStatement pst =
                     con.prepareStatement("SELECT * FROM chernevonlinestore.users WHERE id = ?")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                user = unmap(rs);
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        }
        return user;
    }

    public List<User> getAll() {

        return new ArrayList<>();
    }

    public void updateUser(User user) {
        //TODO make update user method
    }

    private void map(PreparedStatement pst, User user) throws SQLException {
        int k = 1;
        pst.setString(k++, user.getFirstName());
        pst.setString(k++, user.getLastName());
        pst.setString(k++, user.getLogin());
        pst.setString(k++, user.getEmail());
        pst.setString(k++, user.getPhone());
        pst.setString(k++, user.getPassword());
        pst.setString(k++, user.getUserPicturePath());
        pst.setInt(k, user.getRole().getId());
    }

    private User unmap(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("f_name"));
        user.setLastName(rs.getString("l_name"));
        user.setLogin(rs.getString("login"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setPassword(rs.getString("password"));
        user.setUserPicturePath(rs.getString("user_pic_path"));
        user.setRole(Role.getRole(rs.getInt("role_id")));
        return user;
    }

}
