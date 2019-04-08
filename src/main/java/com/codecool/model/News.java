package com.codecool.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class News implements Serializable {
    private String id;
    private String title;
    private String content;
    private LocalDateTime date;
    private String user_id;


    public News(String id, String title, String content, LocalDateTime date, String user_id){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.user_id = user_id;
    }

    public News(String id, String title, String content, LocalDateTime date){
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }




    public LocalDateTime getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPublishingTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }
}

