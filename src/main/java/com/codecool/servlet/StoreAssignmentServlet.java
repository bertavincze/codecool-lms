package com.codecool.servlet;


import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.PageList;
import com.codecool.model.user.User;
import com.codecool.model.user.UserRole;
import com.codecool.service.UserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/storeassignment")
public class StoreAssignmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("title");
        String question = req.getParameter("question");
        String maxScore = req.getParameter("maxScore");
        AssignmentPage assignmentPage = new AssignmentPage(name, question, Integer.parseInt(maxScore));
        PageList.getInstance().addPage(assignmentPage);
        /*req.setAttribute("assignmentPage", assignmentPage);
        req.getRequestDispatcher("sendassignment.jsp").forward(req, resp);*/


    }
}
