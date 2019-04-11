package com.codecool.servlet;


import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.DatabasePageDao;
import com.codecool.dao.database.DatabaseSolutionDao;

import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.TextPage;


import com.codecool.service.dao.*;
import com.codecool.service.servlet.PageUtilService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/updatePage")
public class PageUpdateServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabasePageDao databasePageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(databasePageDao);


            HttpSession session = request.getSession(false);
            String title = request.getParameter("title");

            Page requestedPage = PageUtilService.getRequestedPage(request, pageService, title);
            request.setAttribute("id", requestedPage.getId());

            if (requestedPage instanceof AssignmentPage) {
                request.setAttribute("title", title);
                request.setAttribute("content", ((AssignmentPage) requestedPage).getQuestion());
                request.setAttribute("max", ((AssignmentPage) requestedPage).getMaxScore());

                request.getRequestDispatcher("updateAssignment.jsp").forward(request, response);
            } else if (requestedPage instanceof TextPage) {
                request.setAttribute("title", title);
                request.setAttribute("content", ((TextPage) requestedPage).getContent());
                request.getRequestDispatcher("updateTextpages.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Page doesnt exist");
                request.getRequestDispatcher("handlepage").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabasePageDao databasePageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(databasePageDao);

            String id = request.getParameter("id");
            Page pageToUpdate = new PageUtilService().findPageById(pageService, id);

            if (pageToUpdate instanceof AssignmentPage){
                String title = request.getParameter("title");
                String content = request.getParameter("question");
                pageService.updatePage(pageToUpdate, title, content);
            } else {
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                pageService.updatePage(pageToUpdate, title, content);
            }request.getRequestDispatcher("curriculum").forward(request, response);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}

