package com.codecool.servlet;


import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.user.User;
import com.codecool.service.dao.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            UserService userService = new UserService(userDao, attendanceDao);
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("user");
            userService.deleteUser(user.getId());
            req.getSession(false).invalidate();
            resp.sendRedirect("login");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
