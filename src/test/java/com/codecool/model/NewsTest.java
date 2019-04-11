package com.codecool.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NewsTest {
    private News news;

    @BeforeEach
    void setUp() {
        news = new News("lm89#.", "News One", "news content", LocalDateTime.of(2019, 3, 18, 9, 10 ), "oo77##");
    }

    @AfterEach
    void tearDown() {
        news = null;
    }

    @Test
    void getDate() {
        assertEquals(LocalDateTime.of(2019, 3, 18, 9, 10 ), news.getDate());
    }

    @Test
    void getContent() {
    }

    @Test
    void getId() {
    }

    @Test
    void getTitle() {
    }

    @Test
    void getUser_id() {
    }

    @Test
    void getPublishingTime() {
    }
}
