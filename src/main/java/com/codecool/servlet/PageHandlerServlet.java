package com.codecool.servlet;


import com.codecool.database.PageList;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.curriculum.TextPage;
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
                request.getRequestDispatcher("solution").forward(request, response);
            } else {
                request.getRequestDispatcher("curriculum").forward(request, response);
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

}
