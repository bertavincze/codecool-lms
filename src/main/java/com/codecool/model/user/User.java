package com.codecool.model.user;

import com.codecool.model.curriculum.Solution;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
    private List<Solution> solutions;

    public User(String name, String email, String password, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        if(this.userRole.equals(userRole.STUDENT)) {
            solutions = new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void addSolution(Solution solution) {
        if(!solutions.contains(solution)) {
            addSolution(solution);
        }
    }
}
