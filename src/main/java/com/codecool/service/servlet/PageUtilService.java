package com.codecool.service.servlet;

import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.User;
import com.codecool.service.dao.PageService;
import com.codecool.service.dao.SolutionService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtilService {

    public static Solution findUserSolutionByPage(User user, Page page, SolutionService solutionService) throws SQLException {
        for (Solution solution : solutionService.loadSolutionsByPage(page)) {
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
            if (assignmentPage != null) {
                assignmentMap.put(solution, assignmentPage.getMaxScore());
            }
        }
        return assignmentMap;
    }

    public static List<AssignmentPage> findAssignmentsNotSolved(PageService pageService, List<Solution> solutions, User user) throws SQLException {
        List<String> titleList = new ArrayList<>();
        List<AssignmentPage> assignments = pageService.loadAssignmentPages();
        if (solutions == null) {
            for (AssignmentPage page : assignments) {
                if (page.isPublished()) {
                    assignments.add(page);
                }
            }

        } else {
            for (Solution solution : solutions) {
                titleList.add(solution.getTitle());
            }
            for (AssignmentPage page : assignments) {
                if (!titleList.contains(page.getTitle()) && page.isPublished()) {
                    assignments.add(page);
                }

            }
        }
        return assignments;
    }

    public static Page getRequestedPage(HttpServletRequest request, PageService pageService, String title) throws SQLException {
        Page requestedPage = null;
        for (Page page : pageService.loadPages()) {
            if (page.getTitle().equals(title)) {
                request.setAttribute("page", page);
                requestedPage = page;
                break;
            }
        }
        return requestedPage;
    }

}
