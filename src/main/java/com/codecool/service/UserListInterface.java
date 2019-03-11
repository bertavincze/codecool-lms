package com.codecool.service;

import com.codecool.model.user.User;

import java.util.List;

public interface UserListInterface {
    void addUser(User user);
    List<User> getUsers();

}
