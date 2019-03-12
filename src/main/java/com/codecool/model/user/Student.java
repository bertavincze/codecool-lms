package com.codecool.model.user;

import java.util.ArrayList;

public class Student extends User {

    private List<Solution> solutionList;

    public Student(String name, String email, String password) {
        super(name, email, password);
        solutionList = new ArrayList<>();
    }

    public void addSolution(Solution solution) {
        solutionList.add(solution);
    }
}
