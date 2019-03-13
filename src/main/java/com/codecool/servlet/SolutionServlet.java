package com.codecool.servlet;

import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.Student;

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

        String name = req.getParameter("title");
        String question = req.getParameter("question");
        String answer = req.getParameter("solution");
        Solution solution = new Solution(name, question, answer);
        user.addSolution(solution);
        req.getRequestDispatcher("curriculum").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Student user = (Student) session.getAttribute("user");

        String title = request.getParameter("title");

        for (Solution solution : user.getSolutionList()) {
            if (solution.getTitle().equals(title)) {
                request.setAttribute("solution", solution);
                request.getRequestDispatcher("seesolution.jsp").forward(request, response);
                return;
            }
        }
        request.getRequestDispatcher("404.html").forward(request, response);
    }
}
