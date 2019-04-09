package com.codecool.service;

import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {

    private final DatabaseUserDao userDao;
    private final DatabaseAttendanceDao attendanceDao;


    //public UserService(DatabaseUserDao userDao) {
     //   this.userDao = userDao;
    //}

    public UserService(DatabaseUserDao userDao, DatabaseAttendanceDao attendanceDao) {
        this.userDao = userDao;
        this.attendanceDao = attendanceDao;
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

    public List<User> getUsersWithMap() throws SQLException {
        List<User> users =  new ArrayList<>();
        for (User user : userDao.findUsers()) {
            if(user instanceof Student) {
                Map<LocalDate, Boolean> attendance = attendanceDao.findAttendanceMapByUser(user);
                for ( Map.Entry<LocalDate, Boolean > entry : attendance.entrySet()) {
                    ((Student)user).setAttendance(entry.getKey(), entry.getValue());
                }
                users.add(user);
            }
        }
        return users;
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
