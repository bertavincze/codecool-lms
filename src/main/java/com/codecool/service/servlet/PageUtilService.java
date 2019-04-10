package com.codecool.service.servlet;

import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Solution;
import com.codecool.service.dao.PageService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtilService {

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
