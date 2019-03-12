package com.codecool.service;

import com.codecool.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList ourInstance = new UserList();
    private List<User> userList;

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
