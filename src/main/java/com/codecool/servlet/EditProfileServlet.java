package com.codecool.servlet;

import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editedName")
public class EditProfileServlet extends HttpServlet {

    private User currentUser;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("name");
        getCurrentUser(request).setName(newName);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    private User getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        currentUser = (User) session.getAttribute("user");
        return currentUser;
    }
}
