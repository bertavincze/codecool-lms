package com.codecool.model.curriculum;

import java.io.Serializable;
import java.util.Objects;

public class Solution implements Serializable {
   private String title;
    private String answer;
    private int grade;

    public Solution(String title, String question, String answer) {
        this.title = title;
        this.answer = answer;
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

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
