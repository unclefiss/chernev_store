package com.epam.chernev.service;

import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.User;
import com.epam.chernev.repository.dao.UserDAO;

public class UserService {

    TransactionManager manager;

    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
        manager = new TransactionManager();
    }

    public void createUser(User user) throws RepositoryException {
        manager.doInTransaction(connection -> {
            userDAO.create(connection, user);
            return null;
        });
    }

    public User login(String login, String password) throws RepositoryException {
        return manager.doInTransaction(connection -> {
            User user = userDAO.getByLogin(connection, login);
            if (user != null && password.equalsIgnoreCase(user.getPassword())) {
                return user;
            } else {
                return null;
            }
        });
    }

    public User getUserByLogin(String login) throws RepositoryException {
        return manager.doInTransaction(connection -> userDAO.getByLogin(connection, login) );
    }

    public User getUserById(Long id) throws RepositoryException {
        return manager.doInTransaction(connection -> userDAO.getById(connection, id) );
    }

}
