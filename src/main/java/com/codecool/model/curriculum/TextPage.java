package com.codecool.model.curriculum;

public class TextPage extends Page {

    private String content;
    private boolean isPublished;

    public TextPage(String title, String content) {
        super(title);
        this.content = content;
    }
}
