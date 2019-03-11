package com.codecool.model;

public class AssignmentPage extends Page {

    private String question;
    private int maxScore;
    private boolean isPublished;

    public AssignmentPage(String title, String question, int maxScore) {
        super(title);
        this.question = question;
        this.maxScore = maxScore;
    }

}
