package com.codecool.servlet;

import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;
import com.codecool.service.dao.IDGeneratorService;
import com.codecool.service.dao.UserService;
import com.codecool.service.servlet.UserUtilService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            UserService userService = new UserService(userDao, attendanceDao);
            IDGeneratorService idService = new IDGeneratorService();

            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String userRoleString = req.getParameter("status");
            String image_id = "1";
            String generatedID = idService.generateID();

            if (UserUtilService.validateUserData(name, email, password, userRoleString, userService)) {
                User user = null;
                if (userRoleString.equals("mentor")) {
                    userService.addUser(generatedID,"mentor", name, email,password, image_id);
                    user = new Mentor(generatedID, name, email, password, image_id);
                } else {
                    userService.addUser(generatedID,"student", name, email,password, image_id);
                    user = new Student(generatedID, name, email, password, image_id);
                }
                req.setAttribute("user", user);
                req.getRequestDispatcher("succesfulregist.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("registration.jsp");
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }


}
