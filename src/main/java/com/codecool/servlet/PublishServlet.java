package com.codecool.servlet;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.model.curriculum.Page;
import com.codecool.service.dao.PageService;
import com.codecool.service.servlet.PageUtilService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/publishservlet")
public class PublishServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            String[] published = request.getParameterValues("publish");
            String[] unpublished = request.getParameterValues("unpublish");
            DatabasePageDao pageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(pageDao);
            PageUtilService pageUtilService = new PageUtilService();

            if (published != null) {
                pageUtilService.publishPages(published, pageService);
            }

            if (unpublished != null) {
                pageUtilService.unpublishPages(unpublished, pageService);
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        request.getRequestDispatcher("curriculum").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("curriculum.jsp").forward(request, response);
    }
}
