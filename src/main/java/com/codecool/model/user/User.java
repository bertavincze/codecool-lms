package com.codecool.model.user;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;


    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

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

}
