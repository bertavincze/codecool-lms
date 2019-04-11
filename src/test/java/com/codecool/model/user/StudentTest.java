package com.codecool.model.user;

import com.codecool.model.curriculum.Solution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;
    private Solution solution;

    @BeforeEach
    void setUp() {
        student = new Student("50ff##", "student1", "student@gmail.com", "studentpass", "resources/pics/profile.jpg");
        solution = new Solution("40kk.#", "Solution title", "solved example");
        student.addSolution(solution);
        student.setAttendance(LocalDate.parse("2019-03-20"), true);
        student.setAttendance(LocalDate.parse("2019-03-21"), false);
    }

    @AfterEach
    void tearDown() {
        student = null;
    }

    @Test
    void addSolution() {
        assertEquals(true, student.getSolutionList().size() == 1);
    }

    @Test
    void getSolutionList() {
        assertEquals(1, student.getSolutionList().size());
        assertEquals("40kk.#", student.getSolutionList().get(0).getSolution_id());
    }

    @Test
    void setAttendance() {
        assertEquals(2, student.getAttendance().size());
    }

    @Test
    void getAttendanceRate() {
        assertEquals(2, student.getAttendanceRate());
    }

    @Test
    void getAttendance() {
        assertEquals(true, student.getAttendance().get(LocalDate.parse("2019-03-20")));
        assertEquals(false, student.getAttendance().get(LocalDate.parse("2019-03-21")));
    }
}
