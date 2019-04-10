package com.codecool.service.servlet;

import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.User;
import com.codecool.service.dao.PageService;
import com.codecool.service.dao.SolutionService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtilService {

    public static Solution findUserSolutionByPage(User user, Page page, SolutionService solutionService) throws SQLException {
        for (Solution solution: solutionService.loadSolutionsByPage(page)) {
            if (solution.getUser_id().equals(user.getId())) {
                return solution;
            }
        }
        return null;
    }

    public static void publishPages(String[] pageArray, PageService pageService) throws SQLException {
        for (Page page : pageService.loadPages()) {
            for (String name : pageArray) {
                if (page.getTitle().equals(name)) {
                    page.publish();
                    pageService.updatePagePublishedState(page, true);
                }
            }
        }
    }

    public static void unpublishPages(String[] pageArray, PageService pageService) throws SQLException {
        for (Page page : pageService.loadPages()) {
            for (String name : pageArray) {
                if (page.getTitle().equals(name)) {
                    page.unpublish();
                    pageService.updatePagePublishedState(page, false);
                }
            }
        }
    }

    public static Map<Solution, Integer> getAssignmentMap(List<Solution> solutions, PageService pageService) throws SQLException {
        Map<Solution, Integer> assignmentMap = new HashMap<>();
        for (Solution solution : solutions) {
            AssignmentPage assignmentPage = (AssignmentPage) pageService.findPageByTitle(solution.getTitle());
            if (assignmentPage != null){
                assignmentMap.put(solution, assignmentPage.getMaxScore());
            }
        }
        return assignmentMap;
    }

}
