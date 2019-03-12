package com.codecool.servlet;

import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import com.codecool.database.UserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        User currentUser = null;
        for (User user : UserList.getInstance().getUsers()) {
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                currentUser = user;
                HttpSession session = request.getSession(true);
                session.setAttribute("user", currentUser);
            }
        }

        if (currentUser != null) {
            if (currentUser instanceof Mentor) {
                request.getRequestDispatcher("mentor.html").forward(request, response);
            } else if (currentUser instanceof Student) {
                request.getRequestDispatcher("student.html").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.html").forward(request, response);
        }

    }
}