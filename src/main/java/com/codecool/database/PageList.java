package com.codecool.database;

import com.codecool.model.curriculum.Page;

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

    public void addPage(Page page) {
        pageList.add(page);
    }

    public List<Page> getPageList() {
        return pageList;
    }
}
