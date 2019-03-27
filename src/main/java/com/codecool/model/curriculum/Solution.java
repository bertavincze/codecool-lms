package com.codecool.model.curriculum;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Solution implements Serializable {
    private String title;
    private String id;
    private String answer;
    private int grade;
    private LocalDateTime submissionDate;

    public Solution(String id, String title, String answer) {
        this.id = id;
        this.title = title;
        this.answer = answer;
        this.submissionDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(title, solution.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, answer);
    }

    public String getTitle() {
        return title;
    }

    public String getAnswer() {
        return answer;
    }


    public int getGrade() {
        return grade;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


}
