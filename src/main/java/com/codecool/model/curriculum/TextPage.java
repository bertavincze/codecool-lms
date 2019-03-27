package com.codecool.model.curriculum;

import java.io.Serializable;

public class TextPage extends Page implements Serializable {

    private String content;

    public TextPage(String title, String id, String content) {
        super(title, id);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
