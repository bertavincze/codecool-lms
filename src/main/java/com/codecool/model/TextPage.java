package com.codecool.model;

public class TextPage extends Page {

    private String content;
    private boolean isPublished;

    public TextPage(String title, String content) {
        super(title);
        this.content = content;
    }
}
