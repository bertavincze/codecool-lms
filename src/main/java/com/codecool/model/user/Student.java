package com.codecool.model.user;

import com.codecool.model.curriculum.Solution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Serializable {

    private List<Solution> solutionList;

    public Student(String name, String email, String password) {
        super(name, email, password);
        solutionList = new ArrayList<>();
    }

    public void addSolution(Solution solution) {
        if (!solutionList.contains(solution)) {
            solutionList.add(solution);
        }
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }
}

