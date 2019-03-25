package com.codecool.servlet;

import com.codecool.database.PageList;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/solution")
public class SolutionServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Student user = (Student) session.getAttribute("user");

        String title = req.getParameter("title");
        String question = req.getParameter("question");
        String answer = req.getParameter("solution");
        Solution solution = new Solution(title, question, answer);
        user.addSolution(solution);
        AssignmentPage assignmentPage = (AssignmentPage) PageList.getInstance().findPageByTitle(title);
        assignmentPage.addToSolutionMap(user, solution);
        req.getRequestDispatcher("curriculum").forward(req, resp);
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
