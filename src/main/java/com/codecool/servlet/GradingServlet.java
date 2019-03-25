package com.codecool.servlet;

import com.codecool.database.PageList;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/grader")
public class GradingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int grade = Integer.parseInt(req.getParameter("grade"));
        String title = req.getParameter("title");
        for (Page page : PageList.getInstance().getPageList()) {
            if (page instanceof AssignmentPage) {
                for (Solution solution : page.getSolutionMap().values()) {
                    if (title.equals(solution.getTitle())) {
                        solution.setGrade(grade);
                    }
                }
            }
        }
        req.getRequestDispatcher("studentSolution.jsp").forward(req, resp);
    }

}
