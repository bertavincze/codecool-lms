package com.codecool.servlet;

import com.codecool.dao.database.database.DatabasePageDao;
import com.codecool.model.curriculum.Page;
import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;
import com.codecool.service.dao.PageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/curriculum")
public class CurriculumServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabasePageDao pageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(pageDao);

            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            List<Page> pageList = pageService.loadPages();
            request.setAttribute("pageList", pageList);

            if (user instanceof Mentor) {
                request.getRequestDispatcher("curriculum-mentor.jsp").forward(request, response);
            } else if (user instanceof Student){
                request.getRequestDispatcher("curriculum-student.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
