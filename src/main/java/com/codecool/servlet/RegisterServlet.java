package com.codecool.servlet;

import com.codecool.dao.database.DatabaseMentorDao;
import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;
import com.codecool.dao.database.UserList;
import com.codecool.service.IDGeneratorService;
import com.codecool.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {

    private boolean isValidUserData = true;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseMentorDao mentorDao = new DatabaseMentorDao(connection);
            UserService userService = new UserService(mentorDao);
            IDGeneratorService idService = new IDGeneratorService();

            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String userRoleString = req.getParameter("status");
            String generatedID = idService.generateID();
            userService.addMentor(generatedID,"mentor", name, email,password);
            validateUserData(name, email, password, userRoleString);

            if (isValidUserData) {
                User user = null;
                if (userRoleString.equals("mentor")) {
                    user = new Mentor(generatedID, name, email, password);
                } else if (userRoleString.equals("student")){
                    user = new Student(generatedID, name, email, password);
                }
                UserList.getInstance().addUser(user);
                req.setAttribute("user", user);
                req.getRequestDispatcher("succesfulregist.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("registration.html");
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.html").forward(req, resp);
    }

    private void validateUserData(String name, String email, String password, String userRoleString) {
        for (User user: UserList.getInstance().getUsers()) {
            if (user.getName().equals(name) && user.getEmail().equals(email)) {
                isValidUserData = false;
            } else if (user.getEmail().equals(email) || user.getName().equals(name)) {
                isValidUserData = false;
            } else if (name == null || email == null || password == null || userRoleString == null) {
                isValidUserData = false;
            }
        }

    }
}
