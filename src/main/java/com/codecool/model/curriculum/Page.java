package com.codecool.model.curriculum;

import java.io.Serializable;

public abstract class Page implements Serializable {

    private String title;
    private String id;
    protected boolean isPublished;

    public Page(String title, String id) {
        this.id = id;
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

    public String getId() {
        return id;
    }
}
