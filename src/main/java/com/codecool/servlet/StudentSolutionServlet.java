package com.codecool.servlet;

import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.DatabasePageDao;
import com.codecool.dao.database.DatabaseSolutionDao;
import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.User;
import com.codecool.service.dao.PageService;
import com.codecool.service.dao.SolutionService;
import com.codecool.service.dao.UserService;
import com.codecool.service.servlet.UserUtilService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/solutions")
public class StudentSolutionServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try (Connection connection = getConnection(request.getServletContext())) {
                DatabasePageDao pageDao = new DatabasePageDao(connection);
                PageService pageService = new PageService(pageDao);
                DatabaseSolutionDao solutionDao = new DatabaseSolutionDao(connection);
                SolutionService solutionService = new SolutionService(solutionDao);
                DatabaseUserDao userDao = new DatabaseUserDao(connection);
                DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
                UserService userService = new UserService(userDao, attendanceDao);

                List<AssignmentPage> assignmentList = pageService.loadAssignmentPages();

                List<Solution> solutions = new ArrayList<>();
                for (AssignmentPage page: assignmentList) {
                    solutions.addAll(solutionService.loadSolutionsByPage(page));
                }

                Map<User, Solution> solutionMap = UserUtilService.getSolutionMapForUser(solutions, userService.getUsers());

                request.setAttribute("solutionMap", solutionMap);
                request.getRequestDispatcher("studentSolution.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

}
