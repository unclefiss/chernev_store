package com.epam.chernev.repository;

public interface Repository<T, K> {

    Object getAll();

    T findByKey(K key);

    void delete(K key);

    void add(T item);
}
