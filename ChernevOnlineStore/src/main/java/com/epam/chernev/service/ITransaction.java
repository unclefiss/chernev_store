package com.epam.chernev.service;

import java.sql.Connection;

public interface ITransaction<T> {
    T action(Connection connection);
}
