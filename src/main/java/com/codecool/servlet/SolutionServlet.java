package com.codecool.servlet;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.dao.database.DatabaseSolutionDao;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;
import com.codecool.service.IDGeneratorService;
import com.codecool.service.PageService;
import com.codecool.service.SolutionService;

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
            req.getRequestDispatcher("curriculum").forward(req, resp);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Student user = (Student) session.getAttribute("user");

        String title = request.getParameter("title");
        AssignmentPage assignmentPage = (AssignmentPage) request.getAttribute("assignmentPage");

        Solution solution = findUserSolutionByTitle(user, title);
        if (solution != null) {
            request.setAttribute("solution", solution);
            request.setAttribute("assignmentPage", assignmentPage);
            request.getRequestDispatcher("seesolution.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("404.html").forward(request, response);
        }
    }

    private Solution findUserSolutionByTitle(User user, String title) {
        Student student = (Student) user;
        if (!student.getSolutionList().isEmpty()) {
            for (Solution solution : student.getSolutionList()) {
                if (solution.getTitle().equals(title)) {
                    return solution;
                }
            }
        }
        return null;
    }
}
