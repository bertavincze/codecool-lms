package com.codecool.servlet;


import com.codecool.model.User;
import com.codecool.model.UserRole;
import com.codecool.service.UserList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    UserList userList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        UserRole userRole = UserRole.valueOf(req.getParameter("status"));

        userList.addUser(new User(name, password, userRole));

    }
}
