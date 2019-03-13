package com.codecool.servlet;

import com.codecool.model.user.Mentor;
import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/curriculum")
public class CurriculumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");


        if (user instanceof Mentor) {
            request.getRequestDispatcher("curriculum-mentor.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("curriculum-student.jsp").forward(request, response);
        }
    }
}
