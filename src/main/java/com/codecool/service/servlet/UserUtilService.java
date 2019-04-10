package com.codecool.service.servlet;

import com.codecool.model.user.User;
import com.codecool.service.dao.UserService;

import java.sql.SQLException;

public class UserUtilService {

    public static boolean validateUserData(String name, String email, String password, String userRoleString, UserService userService) throws SQLException {
        boolean isValidUserData = true;
        for (User user: userService.getUsers()) {
            if (user.getName().equals(name) && user.getEmail().equals(email)) {
                isValidUserData = false;
            } else if (user.getEmail().equals(email) || user.getName().equals(name)) {
                isValidUserData = false;
            } else if (name == null || email == null || password == null || userRoleString == null) {
                isValidUserData = false;
            }
        }
        return isValidUserData;
    }

}
