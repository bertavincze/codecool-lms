package com.codecool.model.curriculum;

public class TextPage extends Page {

    private String content;

    public TextPage(String title, String content) {
        super(title);
        this.content = content;
    }
}
