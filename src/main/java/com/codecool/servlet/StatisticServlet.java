package com.codecool.servlet;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.dao.database.DatabaseSolutionDao;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.Student;
import com.codecool.service.dao.PageService;
import com.codecool.service.dao.SolutionService;
import com.codecool.service.servlet.PageUtilService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/stats")
public class StatisticServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext()) ) {
            DatabasePageDao pageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(pageDao);
            DatabaseSolutionDao solutionDao = new DatabaseSolutionDao(connection);
            SolutionService solutionService = new SolutionService(solutionDao);

            HttpSession session = request.getSession(false);
            Student user = (Student) session.getAttribute("user");

            Map<Solution, Integer> assignmentMap = PageUtilService.getAssignmentMap(solutionService.loadSolutionForUser(user), pageService);

            request.setAttribute("assignmentMap", assignmentMap);
            request.getRequestDispatcher("stats.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }




}
