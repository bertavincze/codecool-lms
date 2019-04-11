package com.codecool.model.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("50ff##", "student1", "student@gmail.com", "studentpass", "resources/pics/profile.jpg");
    }

    @AfterEach
    void tearDown() {
        student = null;
    }

    @Test
    void addSolution() {
    }

    @Test
    void getSolutionList() {
    }

    @Test
    void setAttendance() {
    }

    @Test
    void getAttendanceRate() {
    }

    @Test
    void getAttendance() {
    }
}
