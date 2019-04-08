package com.codecool.servlet;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.service.PageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/solutions")
public class StudentSolutionServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try (Connection connection = getConnection(request.getServletContext())) {
                DatabasePageDao pageDao = new DatabasePageDao(connection);
                PageService pageService = new PageService(pageDao);

                List<Page> assignments = pageService.loadPages();
                List<AssignmentPage> assignmentList = new ArrayList<>();
                for (Page page : assignments) {
                    if (page instanceof AssignmentPage && page.isPublished()) {
                        assignmentList.add((AssignmentPage) page);
                    }
                }
                request.setAttribute("assignmentList", assignmentList);
                request.getRequestDispatcher("studentSolution.jsp").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


    }

}
