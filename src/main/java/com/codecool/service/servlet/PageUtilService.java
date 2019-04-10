package com.codecool.service.servlet;

import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.User;
import com.codecool.service.dao.PageService;
import com.codecool.service.dao.SolutionService;

import java.sql.SQLException;

public class PageUtilService {

    public Solution findUserSolutionByPage(User user, Page page, SolutionService solutionService) throws SQLException {
        for (Solution solution: solutionService.loadSolutionsByPage(page)) {
            if (solution.getUser_id().equals(user.getId())) {
                return solution;
            }
        }
        return null;
    }

    public void publishPages(String[] pageArray, PageService pageService) throws SQLException {
        for (Page page : pageService.loadPages()) {
            for (String name : pageArray) {
                if (page.getTitle().equals(name)) {
                    page.publish();
                    pageService.updatePagePublishedState(page, true);
                }
            }
        }
    }

    public void unpublishPages(String[] pageArray, PageService pageService) throws SQLException {
        for (Page page : pageService.loadPages()) {
            for (String name : pageArray) {
                if (page.getTitle().equals(name)) {
                    page.unpublish();
                    pageService.updatePagePublishedState(page, false);
                }
            }
        }
    }
}
