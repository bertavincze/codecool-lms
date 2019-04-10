package com.codecool.servlet;


import com.codecool.dao.database.DatabaseAttendanceDao;
import com.codecool.dao.database.DatabasePageDao;
import com.codecool.dao.database.DatabaseSolutionDao;
import com.codecool.dao.database.DatabaseUserDao;

import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.curriculum.TextPage;
import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import com.codecool.service.dao.IDGeneratorService;
import com.codecool.service.dao.PageService;
import com.codecool.service.dao.SolutionService;
import com.codecool.service.dao.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/handlepage")
public class PageHandlerServlet extends AbstractServlet {

    IDGeneratorService idGeneratorService = new IDGeneratorService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabasePageDao databasePageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(databasePageDao);

            if (request.getParameter("question") != null) {
                String title = request.getParameter("title");
                String question = request.getParameter("question");
                String maxScore = request.getParameter("maxScore");
                String id = idGeneratorService.generateID();
                AssignmentPage assignmentPage = new AssignmentPage(id, title, question, Integer.parseInt(maxScore));
                pageService.addPage(assignmentPage);

            } else {
                String title = request.getParameter("title");
                String content = request.getParameter("text");
                String id = idGeneratorService.generateID();
                TextPage textPage = new TextPage(id, title, content);
                pageService.addPage(textPage);
            }
            request.getRequestDispatcher("curriculum").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException (e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            UserService userService = new UserService(userDao, attendanceDao);
            DatabasePageDao databasePageDao = new DatabasePageDao(connection);
            PageService pageService = new PageService(databasePageDao);
            DatabaseSolutionDao solutionDao = new DatabaseSolutionDao(connection);
            SolutionService solutionService = new SolutionService(solutionDao);

            List<User> users = userService.getUsers();

            String title = request.getParameter("title");
            String name = request.getParameter("name");
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");

            Page requestedPage = null;
            for (Page page : pageService.loadPages()) {
                if (page.getTitle().equals(title)) {
                    request.setAttribute("page", page);
                    requestedPage = page;
                    break;
                }
            }

            if (isAssignmentPage(requestedPage)) {
                if (user instanceof Student) {
                    Solution solution = findUserSolutionByPage(user, requestedPage, solutionService);
                    if (solution != null) {
                        request.setAttribute("assignmentPage", requestedPage);
                        request.setAttribute("solution", solution);
                        request.getRequestDispatcher("solution?title=" + solution.getTitle()).forward(request, response);

                    } else {
                        request.getRequestDispatcher("sendassignment.jsp").forward(request, response);
                    }

                } else if (user instanceof Mentor) {
                    boolean isEditRequest = Boolean.parseBoolean(request.getParameter("edit"));
                    if (isEditRequest) {
                        for (User u : users){
                            if (u instanceof Student && u.getName().equals(name)) {
                                Solution solution = findUserSolutionByPage(u, requestedPage, solutionService);
                                request.setAttribute("solution", solution);
                            }
                        }
                        request.setAttribute("assignmentPage", requestedPage);
                        request.getRequestDispatcher("seesolution.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("seeassignment.jsp").forward(request, response);
                    }
                }

            } else if (isTextPage(requestedPage)) {
                request.getRequestDispatcher("seetextpage.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private boolean isAssignmentPage(Page page) {
        return page instanceof AssignmentPage;
    }

    private boolean isTextPage(Page page) {
        return page instanceof TextPage;
    }

    private Solution findUserSolutionByPage(User user, Page page, SolutionService solutionService) throws SQLException {
        for (Solution solution: solutionService.loadSolutionsByPage(page)) {
            if (solution.getUser_id().equals(user.getId())) {
                return solution;
            }
        }
        return null;
    }
}
