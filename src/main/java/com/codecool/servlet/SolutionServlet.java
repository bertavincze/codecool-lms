package com.codecool.servlet;

import com.codecool.dao.database.database.DatabasePageDao;
import com.codecool.dao.database.database.DatabaseSolutionDao;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.Student;
import com.codecool.service.dao.IDGeneratorService;
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

@WebServlet("/solution")
public class SolutionServlet extends AbstractServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            HttpSession session = req.getSession(false);
            IDGeneratorService idService = new IDGeneratorService();
            Student user = (Student) session.getAttribute("user");
            DatabaseSolutionDao solutionDao = new DatabaseSolutionDao(connection);
            SolutionService solutionService = new SolutionService(solutionDao);
            DatabasePageDao pageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(pageDao);

            String title = req.getParameter("title");
            String answer = req.getParameter("solution");
            String generatedID = idService.generateID();

            Solution solution = new Solution(generatedID, title, answer);
            user.addSolution(solution);
            solutionService.addSolution(generatedID, user.getId(), title, answer, solution.getSubmissionDate());

            AssignmentPage assignmentPage = (AssignmentPage) pageService.findPageByTitle(title);
            assignmentPage.addToSolutionMap(user, solution);
            pageService.addToSolutionMap(solution, user, assignmentPage);

            req.getRequestDispatcher("curriculum").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            HttpSession session = request.getSession(false);
            Student user = (Student) session.getAttribute("user");
            DatabaseSolutionDao solutionDao = new DatabaseSolutionDao(connection);
            SolutionService solutionService = new SolutionService(solutionDao);

            AssignmentPage assignmentPage = (AssignmentPage) request.getAttribute("assignmentPage");

            Solution solution = PageUtilService.findUserSolutionByPage(user, assignmentPage, solutionService);
            if (solution != null) {
                request.setAttribute("solution", solution);
                request.setAttribute("assignmentPage", assignmentPage);
                request.getRequestDispatcher("seesolution.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("404.html").forward(request, response);
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
