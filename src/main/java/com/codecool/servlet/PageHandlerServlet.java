package com.codecool.servlet;


<<<<<<< HEAD
import com.codecool.database.PageList;
import com.codecool.database.UserList;
=======
import com.codecool.dao.database.PageList;
>>>>>>> Andi
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.curriculum.TextPage;
import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/handlepage")
public class PageHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("question") != null) {
            String name = request.getParameter("title");
            String question = request.getParameter("question");
            String maxScore = request.getParameter("maxScore");
            AssignmentPage assignmentPage = new AssignmentPage(name, question, Integer.parseInt(maxScore));
            PageList.getInstance().addPage(assignmentPage);
        } else {
            String name = request.getParameter("title");
            String content = request.getParameter("text");
            TextPage textPage = new TextPage(name, content);
            PageList.getInstance().addPage(textPage);
        }
        request.getRequestDispatcher("curriculum").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String name = request.getParameter("name");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        Page requestedPage = null;
        for (Page page : PageList.getInstance().getPageList()) {
            if (page.getTitle().equals(title)) {
                request.setAttribute("page", page);
                requestedPage = page;
                break;
            }
        }

        if (isAssignmentPage(requestedPage)) {
            if (user instanceof Student) {
                Solution solution = findUserSolutionByTitle(user, requestedPage.getTitle());
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
                    for (User u: UserList.getInstance().getUsers()){
                        if (u instanceof Student && u.getName().equals(name)) {
                            Solution solution = findUserSolutionByTitle(u, requestedPage.getTitle());
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
    }

    private boolean isAssignmentPage(Page page) {
        return page instanceof AssignmentPage;
    }

    private boolean isTextPage(Page page) {
        return page instanceof TextPage;
    }

    private Solution findUserSolutionByTitle(User user, String title) {
        Student student = (Student) user;
        if (!student.getSolutionList().isEmpty()) {
            for (Solution solution : student.getSolutionList()) {
                if (solution.getTitle().equals(title)) {
                    return solution;
                }
            }
        }
        return null;
    }
}
