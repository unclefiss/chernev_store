package com.epam.chernev.service;

import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.repository.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class TransactionManager {

    Logger log = Logger.getLogger(TransactionManager.class.getName());

    <T> T doInTransaction(ITransaction<T> action) throws RepositoryException {

        T object;
        Connection con = DBUtils.getConnection();
        try {
            assert con != null;
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            object = action.action(con);

            con.commit();
            con.close();
        } catch (SQLException e) {
            try {
                con.rollback();
                con.close();
            } catch (SQLException throwables) {
                log.warning(throwables.getMessage());
            }
            throw new RepositoryException(e.getMessage());
        }
        return object;
    }
}
