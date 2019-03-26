package com.codecool.service;

import com.codecool.dao.database.DatabaseMentorDao;

import java.sql.SQLException;

public class UserService {

    private final DatabaseMentorDao mentorDao;

    public UserService(DatabaseMentorDao mentorDao) {
        this.mentorDao = mentorDao;
    }


    public void addUser(String userID, String role, String name, String email, String password) throws SQLException {

        try {
            mentorDao.addUser(userID, role, name, email, password); // Insert data here);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
