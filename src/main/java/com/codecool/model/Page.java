package com.codecool.model;

public abstract class Page {

    private String title;
    private boolean isPublished;

    public Page(String title) {
        this.title = title;
        this.isPublished = false;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void publish() {
        isPublished = true;
    }

    public void unpublish() {
        isPublished = false;
    }
}
