package com.codecool.model.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mentor extends User {

    private List<String> urlList;

    public Mentor(String name, String email, String password) {
        super(name, email, password);
        urlList = new ArrayList<>(Arrays.asList("mentor.html", "curriculum-mentor.html", "submissions.html"));
        // URLs accessible by Mentor user. If you make a new html page, add it here.
    }

    public List<String> getUrlList() {
        return urlList;
    }
}
