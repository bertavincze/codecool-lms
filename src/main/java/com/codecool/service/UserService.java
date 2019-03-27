package com.codecool.service;

import com.codecool.dao.database.DatabaseUserDao;

import java.sql.SQLException;

public class UserService {

    private final DatabaseUserDao mentorDao;

    public UserService(DatabaseUserDao mentorDao) {
        this.mentorDao = mentorDao;
    }


    public void addUser(String userID, String role, String name, String email, String password) throws SQLException {
        try {
            mentorDao.addUser(userID, role, name, email, password); // Insert data here);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateName(String id, String name) {
        try {
            mentorDao.
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
