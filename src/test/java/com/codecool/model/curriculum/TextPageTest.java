package com.codecool.model.curriculum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextPageTest {

    private TextPage textPage;

    @BeforeEach
    void setUp() {
        this.textPage = new TextPage("sd67#.", "Learn this", "Many words");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getContent() {
        assertEquals("Many words", textPage.getContent());
    }
}
