package com.epam.chernev.repository;

import com.epam.chernev.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository implements Repository<User, String> {

    private final HashMap<String, User> users;

    public UserRepository() {
        users = new HashMap<>();
        init();
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findByKey(String key) {
        return users.getOrDefault(key, null);
    }

    @Override
    public void delete(String key) {
        users.remove(key);
    }

    @Override
    public void add(User item) {
        users.put(item.getLogin(), item);
    }

    public void init() {
        users.put("user", new User.Builder().addLogin("user").build());
        users.put("login", new User.Builder().addLogin("login").build());
        users.put("admin", new User.Builder().addLogin("admin").build());
    }

}
