package com.codecool.model.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student extends User {

    //private List<Solution> solutionList;
    private List<String> urlList;

    public Student(String name, String email, String password) {
        super(name, email, password);
        //solutionList = new ArrayList<>();
        urlList = new ArrayList<>(Arrays.asList("student.html", "curriculum-student.html", "stats.html"));
        // URLs accessible by Student user. If you make a new html page, add it here.
    }

    //public void addSolution(Solution solution) {
    //    solutionList.add(solution);
    //}
}
