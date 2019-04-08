package com.codecool.servlet;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.dao.database.DatabaseSolutionDao;
import com.codecool.dao.database.PageList;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.service.PageService;
import com.codecool.service.SolutionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/grader")
public class GradingServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseSolutionDao solutionDao = new DatabaseSolutionDao(connection);
            SolutionService solutionService = new SolutionService(solutionDao);
            DatabasePageDao pageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(pageDao);
            int grade = Integer.parseInt(req.getParameter("grade"));
            String title = req.getParameter("title");
            for (Page page : pageService.loadPages()) {
                if (page instanceof AssignmentPage) {
                    for (Solution solution : ((AssignmentPage) page).getSolutionMap().values()) {
                        if (title.equals(solution.getTitle())) {
                            solution.setGrade(grade);
                            solutionService.updateSolution(solution.getSolution_id(), grade);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        resp.sendRedirect("solutions");
    }

}
