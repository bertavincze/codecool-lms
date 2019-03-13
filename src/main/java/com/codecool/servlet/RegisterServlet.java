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
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private boolean isValidUserData = false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userRoleString = req.getParameter("status");

        validateUserData(name, email, password, userRoleString);

        if (isValidUserData) {
            User user = null;
            if (userRoleString.equals("mentor")) {
                user = new Mentor(name, email, password);
            } else if (userRoleString.equals("student")){
                user = new Student(name, email, password);
            }
            UserList.getInstance().addUser(user);
            req.setAttribute("user", user);
            req.getRequestDispatcher("succesfulregist.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("registration.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.html").forward(req, resp);
    }

    private void validateUserData(String name, String email, String password, String userRoleString) throws IOException {
        for (User user: UserList.getInstance().getUsers()) {
            if (user.getName().equals(name) && user.getEmail().equals(email)) {
                isValidUserData = false;
            } else if (user.getEmail().equals(email) || user.getName().equals(name)) {
                isValidUserData = false;
            } else if (name == null || email == null || password == null || userRoleString == null) {
                isValidUserData = false;
            }
        }
        isValidUserData = true;
    }
}
