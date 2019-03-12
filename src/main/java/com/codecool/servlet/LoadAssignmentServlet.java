package com.codecool.servlet;

import com.codecool.database.PageList;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loadassignment")
public class LoadAssignmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String assignmentTitle = request.getParameter("assignmentTitle");


        for (Page page : PageList.getInstance().getPageList()) {
            if (page instanceof AssignmentPage) {
                if (page.getTitle().equals(assignmentTitle)) {
                    request.setAttribute("assignmentPage", page);
                    request.getRequestDispatcher("sendassignment.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("404.html").forward(request, response);
            }

        }
    }
}
