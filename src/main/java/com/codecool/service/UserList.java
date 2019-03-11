package com.codecool.service;

import com.codecool.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList ourInstance = new UserList();
    private List<User> userList = null;

    public static UserList getInstance() {
        return ourInstance;
    }

    private UserList() {
        userList = new ArrayList<>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public List<User> getUsers() {
        return userList;
    }
}
