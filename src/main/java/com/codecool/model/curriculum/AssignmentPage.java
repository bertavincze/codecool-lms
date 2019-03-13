package com.codecool.model.curriculum;

import java.io.Serializable;

public class AssignmentPage extends Page implements Serializable {

    private String question;
    private int maxScore;
    private int actualScore;

    public AssignmentPage(String title, String question, int maxScore) {
        super(title);
        this.question = question;
        this.maxScore = maxScore;
    }

    public String getQuestion() {
        return question;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }
}
