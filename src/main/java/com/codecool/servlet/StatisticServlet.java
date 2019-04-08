package com.codecool.servlet;

import com.codecool.dao.database.DatabasePageDao;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.Student;
import com.codecool.service.PageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/stats")
public class StatisticServlet extends AbstractServlet {
    List<Solution> solutions;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext()) ) {
            DatabasePageDao pageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(pageDao);

            HttpSession session = request.getSession(false);
            Student user = (Student) session.getAttribute("user");
            solutions = user.getSolutionList();
            Map<Solution, Integer> assignmentMap= new HashMap<>();

            for (Solution solution : solutions) {
                AssignmentPage assignmentPage = findAssignmentsByTitle(solution.getTitle(), pageService);
                if (assignmentPage != null){
                    assignmentMap.put(solution, assignmentPage.getMaxScore());
                }
            }

            request.setAttribute("assignmentMap", assignmentMap);
            request.getRequestDispatcher("stats.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private AssignmentPage findAssignmentsByTitle(String title, PageService pageService) throws SQLException {
            for (Page page : pageService.loadPages()) {
                if (page instanceof AssignmentPage) {
                    if (((AssignmentPage)page).getTitle().equals(title)) {
                        return (AssignmentPage) page;
                    }
                }
            }
            return null;
    }


}
