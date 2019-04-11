package com.codecool.model.curriculum;

import com.codecool.model.user.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentPageTest {

    private AssignmentPage assignmentPage;

    @BeforeEach
    void setUp() {
        this.assignmentPage = new AssignmentPage("cd34#.", "A very hard assignment",
            "A very hard question?", 5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addToSolutionMap() {
        Solution solution = new Solution("ef45#.",
            "A very hard assignment", "I don't know :(");
        Student student = new Student("jk35#.", "Smart Dude",
            "student@student.com", "password", "2");
        assignmentPage.addToSolutionMap(student, solution);
        assertTrue(assignmentPage.getSolutionMap().containsKey(student) && assignmentPage.getSolutionMap().containsValue(solution));
    }

    @Test
    void getQuestion() {
        assertEquals("A very hard question?", assignmentPage.getQuestion());
    }

    @Test
    void getMaxScore() {
        assertEquals(5, assignmentPage.getMaxScore());
    }

}
