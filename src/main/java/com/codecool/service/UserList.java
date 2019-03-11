package com.codecool.service;

import com.codecool.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserList implements UserListInterface {
    private static UserList ourInstance = new UserList();
    private List<User> userList = null;

    public static UserList getInstance() {
        return ourInstance;
    }

    private UserList() {
        userList = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public List<User> getUsers() {
        return userList;
    }
}
