package com.codecool.service;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.model.curriculum.Page;

import java.sql.SQLException;
import java.util.List;

public class PageService {

    private final DatabasePageDao pageDao;

    public PageService(DatabasePageDao pageDao) {
        this.pageDao = pageDao;
    }

    public void addPage(Page page) throws SQLException {
        pageDao.addPage(page);
    }

    public void updatePagePublishedState(Page page, boolean isPublished) throws SQLException {
        pageDao.updatePagePublishedState(page, isPublished);

    }

    public List<Page> loadPages() throws SQLException {
        return pageDao.loadAllPages();
    }
}
