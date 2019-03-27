package com.codecool.service;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.model.curriculum.Page;

import java.sql.SQLException;

public class PageService {

    private final DatabasePageDao pageDao;

    public PageService(DatabasePageDao pageDao) {
        this.pageDao = pageDao;
    }

    public void addPage(Page page) throws SQLException {
        try {
            pageDao.addPage(page);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatePagePublishedState(Page page, boolean isPublished) throws SQLException {
        try {
            pageDao.updatePagePublishedState(page, isPublished);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
