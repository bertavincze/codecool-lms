package com.codecool.service.dao;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.curriculum.TextPage;
import com.codecool.model.user.User;

import java.sql.SQLException;
import java.util.List;

public class PageService {

    private DatabasePageDao pageDao;

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

    public List<AssignmentPage> loadAssignmentPages() throws SQLException {
        return pageDao.loadAssignmentPages();
    }

    public List<TextPage> loadTextPages() throws SQLException {
        return pageDao.loadTextPages();
    }

    public void updatePage(Page page, String title, String content) throws SQLException{
        pageDao.updatePage(page, title, content);
    }

    public void deletePage(String id) throws SQLException{
        pageDao.removePage(id);
    }
}
