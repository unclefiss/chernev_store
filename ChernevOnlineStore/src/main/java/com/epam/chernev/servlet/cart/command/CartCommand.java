package com.epam.chernev.servlet.cart.command;

import com.epam.chernev.exception.RepositoryException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CartCommand {

    void process(HttpServletRequest request, HttpServletResponse response) throws RepositoryException, ServletException, IOException;

}
