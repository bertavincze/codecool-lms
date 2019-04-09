package com.codecool.servlet;

import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.DatabasePageDao;
import com.codecool.dao.database.DatabaseSolutionDao;
import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.User;
import com.codecool.service.PageService;
import com.codecool.service.SolutionService;
import com.codecool.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

                List<Page> assignments = pageService.loadPages();
                List<AssignmentPage> assignmentList = new ArrayList<>();
                for (Page page : assignments) {
                    if (page instanceof AssignmentPage && page.isPublished()) {
                        assignmentList.add((AssignmentPage) page);
                    }
                }

                List<Solution> solutions = new ArrayList<>();
                for (AssignmentPage page: assignmentList) {
                    solutions.addAll(solutionService.loadSolutionsByPage(page));
                }

                Map<User, Solution> solutionMap = new HashMap<>();

                for (Solution solution: solutions) {
                    for (User user: userService.getUsers()) {
                        if (solution.getUser_id().equals(user.getId())) {
                            solutionMap.put(user, solution);
                        }
                    }
                }

                request.setAttribute("solutionMap", solutionMap);
                request.getRequestDispatcher("studentSolution.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


    }

}
