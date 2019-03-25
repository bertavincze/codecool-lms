package com.codecool.servlet;

import com.codecool.database.PageList;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/solutions")
public class StudentSolutionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Page> assignments = PageList.getInstance().getPageList();
            List<AssignmentPage> assignmentList = new ArrayList<>();
            for (Page page : assignments) {
                if (page instanceof AssignmentPage) {
                    assignmentList.add((AssignmentPage) page);
                }
            }
            request.setAttribute("assignmentList", assignmentList);
            request.getRequestDispatcher("studentSolution.jsp").forward(request, response);

    }

}
