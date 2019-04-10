package com.codecool.service.dao;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.User;

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

    public Page findPageByTitle(String title) throws SQLException {
        for (Page page : loadPages()) {
            if (page.getTitle().equals(title)) {
                return page;
            }
        }
        return null;
    }

    public void addToSolutionMap(Solution solution, User user, Page page) throws SQLException {
        pageDao.addToSolutionMap(solution, user, page);
    }
}
