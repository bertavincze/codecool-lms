package com.codecool.model.curriculum;

public abstract class Page {

    private String title;
    protected boolean isPublished;

    public Page(String title) {
        this.title = title;
        this.isPublished = false;
    }

    public String getTitle(){
        return this.title;
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
