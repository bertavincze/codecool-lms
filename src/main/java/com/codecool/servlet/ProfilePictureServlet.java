package com.codecool.servlet;


import com.codecool.dao.database.database.DatabaseAttendanceDao;
import com.codecool.dao.database.database.DatabaseUserDao;
import com.codecool.model.user.User;
import com.codecool.service.dao.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/changePic")
public class ProfilePictureServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pictures.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            UserService userService = new UserService(userDao, attendanceDao);
            User user = (User) req.getSession().getAttribute("user");

            String newImgId = req.getParameter("pic");

            userService.updatePic(user.getId(), newImgId);
            user.setImage_id(newImgId);
            req.setAttribute("user", user);
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } catch (ServletException | SQLException ex) {
            req.setAttribute("error", "Oops, something went wrong, please try again.");
            doGet(req, resp);
        }
    }
}
