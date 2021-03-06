package com.codecool.model.curriculum;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Solution implements Serializable {
    private String title;
    private String answer;
    private int grade;
    private LocalDateTime submissionDate;
    private String solution_id;
    private String user_id;

    public Solution(String solution_id, String title, String answer) {
        this.title = title;
        this.answer = answer;
        this.submissionDate = LocalDateTime.now();
        this.solution_id = solution_id;
    }

    public Solution(String solution_id, String user_id, String title, String answer, LocalDateTime localDateTime) {
        this.title = title;
        this.user_id = user_id;
        this.answer = answer;
        this.submissionDate = localDateTime;
        this.solution_id = solution_id;
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

    public String getSolution_id() {
        return solution_id;
    }

    public String getUser_id() {
        return user_id;
    }
}
