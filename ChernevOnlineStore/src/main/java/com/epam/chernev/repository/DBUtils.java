package com.epam.chernev.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBUtils {

    private static final Logger log = Logger.getLogger(DBUtils.class.getName());

    private DBUtils() {

    }

    public static Connection getConnection() {
        try {
            log.info("Start creating connection");
            String username = "root";
            String password = "1111";
            String url = "jdbc:mysql://127.0.0.1:3306/" +
                    "chernevonlinestore?useUnicode=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.warning(e.getMessage());
        }
        return null;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException throwables) {
            log.warning(throwables.getMessage());
        }
    }
}
