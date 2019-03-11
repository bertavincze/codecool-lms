package com.codecool.model;

import java.util.ArrayList;
import java.util.List;

public class PageList {

    private static PageList ourInstance = new PageList();
    private List<Page> pageList;

    private PageList() {
        this.pageList = new ArrayList<>();
    }

    public static PageList getInstance() {
        return ourInstance;
    }
}
