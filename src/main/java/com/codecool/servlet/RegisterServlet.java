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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userRoleString = req.getParameter("status").toUpperCase();

        User user;
        if (userRoleString.equals("mentor")) {
            user = new Mentor(name, email, password);
        } else {
            user = new Student(name, email, password);
        }
        UserList.getInstance().addUser(user);
        req.setAttribute("user", user);
        req.getRequestDispatcher("succesfulregist.jsp").forward(req, resp);


    }
}
