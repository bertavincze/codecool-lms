package com.codecool.model.curriculum;

import java.util.Objects;

public class Solution {
    String title;
    String answer;

    public Solution(String title, String answer) {
        this.title = title;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(title, solution.title) &&
            Objects.equals(answer, solution.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, answer);
    }
}
