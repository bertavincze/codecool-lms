package com.codecool.servlet;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.dao.database.PageList;
import com.codecool.model.curriculum.Page;
import com.codecool.service.PageService;

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

            if (published != null) {
                for (Page page : PageList.getInstance().getPageList()) {
                    for (String name : published) {
                        if (page.getTitle().equals(name)) {
                            page.publish();
                            pageService.updatePagePublishedState(page, true);
                        }
                    }
                }
            }

            if (unpublished != null) {
                for (Page page : PageList.getInstance().getPageList()) {
                    for (String name : unpublished) {
                        if (page.getTitle().equals(name)) {
                            page.unpublish();
                            pageService.updatePagePublishedState(page, false);
                        }
                    }
                }
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
