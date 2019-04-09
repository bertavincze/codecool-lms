package com.codecool.service;

import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.user.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final DatabaseUserDao userDao;


    public UserService(DatabaseUserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() throws SQLException {
        return userDao.findUsers();
    }

    public void addUser(String userID, String role, String name, String email, String password, String image_id) throws SQLException {
        try {
            userDao.addUser(userID, role, name, email, password, image_id); // Insert data here);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateName(String id, String name) {
        try {
            userDao.updateName(id, name); // Insert data here);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateEmail(String id, String email) {
        try {
            userDao.updateEmail(id, email); // Insert data here);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatePassword(String id, String password) {
        try {
            userDao.updatePassword(id, password); // Insert data here);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatePic(String id, String imageId) throws SQLException{
        userDao.changeProfilePic(id, imageId);
    }
}
