package com.codecool.servlet;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.model.curriculum.Page;
import com.codecool.service.dao.PageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/deletePage")
public class DeletePageServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabasePageDao pageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(pageDao);

            String title = req.getParameter("title");
            Page page = pageService.findPageByTitle(title);

            String id = page.getId();
            pageService.deletePage(id);
            req.setAttribute("success", "Page successfully deleted");
            req.getRequestDispatcher("updatePages").forward(req, resp);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
