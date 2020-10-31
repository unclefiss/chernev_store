package com.epam.chernev.exception;

public class RepositoryException extends Exception {

    private static final long serialVersionUID = 7637065716761463308L;

    public RepositoryException() {

    }

    public RepositoryException(String message) {
        super(message);
    }
}
