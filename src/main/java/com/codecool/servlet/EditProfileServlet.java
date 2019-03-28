package com.codecool.servlet;

import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/editedName")
public class EditProfileServlet extends AbstractServlet {

    private User currentUser;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            User user =  getCurrentUser(request);
            String newName = request.getParameter("name");
            user.setName(newName);
            userDao.updateName(user.getId(), newName);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private User getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        currentUser = (User) session.getAttribute("user");
        return currentUser;
    }
}
