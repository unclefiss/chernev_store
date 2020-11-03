package com.epam.chernev.repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBUtils {

    private static final Logger log = Logger.getLogger(DBUtils.class.getName());

    private DBUtils() {

    }

    public static Connection getConnection() {
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/chernev");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            log.warning(e.getMessage());
        }
        return c;
    }

}
