package com.codecool.model.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MentorTest {

    private Mentor user;

    @BeforeEach
    void setUp() {
        this.user = new Mentor("ab12.#", "Mentor Guy",
            "mentor@mentor.com", "password", "1");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        assertEquals("ab12.#", user.getId());
    }

    @Test
    void getName() {
        assertEquals("Mentor Guy", user.getName());
    }

    @Test
    void getEmail() {
        assertEquals("mentor@mentor.com", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void getPasswordDisplay() {
        assertEquals("********", user.getPasswordDisplay());
    }

    @Test
    void getImage_id() {
        assertEquals("1", user.getImage_id());
    }

}
