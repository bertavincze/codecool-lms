package com.codecool.model.curriculum;

public class AssignmentPage extends Page {

    private String question;
    private int maxScore;
    private String solution;

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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
