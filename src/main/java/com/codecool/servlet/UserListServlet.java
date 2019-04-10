package com.codecool.servlet;

import com.codecool.dao.database.database.DatabaseAttendanceDao;
import com.codecool.dao.database.database.DatabaseUserDao;
import com.codecool.service.dao.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            UserService userService = new UserService(userDao, attendanceDao);

            request.setAttribute("userList", userService.getUsers());
            request.getRequestDispatcher("userlist.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
