package com.codecool.model.user;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    private String image_id;


    public User(String id, String name, String email, String password, String image_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.image_id = image_id;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordDisplay() {
        String hiddenPassword ="";
        for (int i = 0; i < password.length(); i++){
            hiddenPassword = hiddenPassword+"*";
        }
        return hiddenPassword;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }
}
