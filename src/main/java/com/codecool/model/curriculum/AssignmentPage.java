package com.codecool.model.curriculum;

import com.codecool.model.user.Student;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AssignmentPage extends Page implements Serializable {

    private String question;
    private int maxScore;
    private Map<Student, Solution> studentSolutionMap;

    public AssignmentPage(String title, String id, String question, int maxScore) {
        super(title, id);
        this.question = question;
        this.maxScore = maxScore;
        this.studentSolutionMap = new HashMap<>();
    }

    public void addToSolutionMap(Student student, Solution solution) {
        studentSolutionMap.put(student, solution);
    }

    public String getQuestion() {
        return question;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public Map<Student, Solution> getSolutionMap() {
        return studentSolutionMap;
    }

}
