package com.codecool.model.curriculum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private Solution solution;

    @BeforeEach
    void setUp() {
        this.solution = new Solution("ef45#.", "gh67#.",
            "A very hard assignment", "I don't know :(",
            LocalDateTime.of(2019, Month.MARCH, 11, 8, 10));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTitle() {
        assertEquals("A very hard assignment", solution.getTitle());
    }

    @Test
    void getAnswer() {
        assertEquals("I don't know :(", solution.getAnswer());
    }

    @Test
    void getGrade() {
        solution.setGrade(5);
        assertEquals(5, solution.getGrade());
    }

    @Test
    void getSubmissionDate() {
        assertEquals(LocalDateTime.of(2019, Month.MARCH, 11, 8, 10),
            solution.getSubmissionDate());
    }

    @Test
    void getSolution_id() {
        assertEquals("ef45#.", solution.getSolution_id());
    }

    @Test
    void getUser_id() {
        assertEquals("gh67#.", solution.getUser_id());
    }
}
