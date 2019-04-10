package com.codecool.service.servlet;

import com.codecool.model.user.Student;

import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.User;
import com.codecool.service.dao.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class UserUtilService {

    public static Student findStudentByName(String name, List<User> users) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return (Student) user;
            }
        }
        return null;
    }

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
    public static Map<User, Solution> getSolutionMapForUser(List<Solution> solutions, List<User> users){
        Map<User, Solution> solutionMap = new HashMap<>();
        for (Solution solution: solutions) {
            for (User user: users) {
                if (solution.getUser_id().equals(user.getId())) {
                    solutionMap.put(user, solution);
                }
            }
        }
        return solutionMap;
    }

}
