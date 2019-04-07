package com.codecool.servlet;

import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.dao.database.UserList;
import com.codecool.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/userlist")
public class UserListServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())){
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new UserService(userDao);
            request.setAttribute("userList", userService.getUsers());
            request.getRequestDispatcher("userlist.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
