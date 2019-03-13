package com.codecool.model.curriculum;

import java.io.Serializable;

public class TextPage extends Page implements Serializable {

    private String content;

    public TextPage(String title, String content) {
        super(title);
        this.content = content;
    }
}
