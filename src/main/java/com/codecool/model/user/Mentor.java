package com.codecool.model.user;

import java.io.Serializable;

public class Mentor extends User implements Serializable {

    public Mentor(String id, String name, String email, String password) {
        super(id, name, email, password);
    }
}
