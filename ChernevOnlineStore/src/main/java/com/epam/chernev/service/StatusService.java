package com.epam.chernev.service;

import com.epam.chernev.exception.RepositoryException;
import com.epam.chernev.model.Status;
import com.epam.chernev.repository.dao.StatusDAO;

import java.util.List;

public class StatusService {

    private final TransactionManager manager;

    private final StatusDAO statusDAO;

    public StatusService() {
        statusDAO = new StatusDAO();
        manager = new TransactionManager();
    }

    public List<Status> getCategories() throws RepositoryException {
        return manager.doInTransaction(statusDAO::getCategories);
    }

    public List<Status> getBrands() throws RepositoryException {
        return manager.doInTransaction(statusDAO::getBrands);
    }

    public List<Status> getOrderStatuses() throws RepositoryException {
        return manager.doInTransaction(statusDAO::getOrderStatuses);
    }

}
