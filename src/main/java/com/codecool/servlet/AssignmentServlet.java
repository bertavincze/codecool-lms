package com.codecool.servlet;


import com.codecool.database.PageList;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.curriculum.TextPage;
import com.codecool.model.user.Student;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/assignment")
public class AssignmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        if (req.getParameter("question") != null) {
            String name = req.getParameter("title");
            String question = req.getParameter("question");
            String maxScore = req.getParameter("maxScore");
            AssignmentPage assignmentPage = new AssignmentPage(name, question, Integer.parseInt(maxScore));
            PageList.getInstance().addPage(assignmentPage);
        } else {
            String name = req.getParameter("title");
            String content = req.getParameter("text");
            TextPage textPage = new TextPage(name, content);
            PageList.getInstance().addPage(textPage);
        }
        req.getRequestDispatcher("mentor.html").forward(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String title = request.getParameter("title");
        HttpSession session = request.getSession(false);
        Student user = (Student) session.getAttribute("user");

        for (Page page : PageList.getInstance().getPageList()) {
            if (page.getTitle().equals(title)) {
                request.setAttribute("page", page);
                if (page instanceof AssignmentPage) {
                    for (Solution solution : user.getSolutionList()) {
                        if (solution.getTitle().equals(title)) {
                            request.getRequestDispatcher("seesolution.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("sendassignment.jsp").forward(request, response);

                        }
                    }
                } else if (page instanceof TextPage) {
                    request.getRequestDispatcher("seetextpage.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("404.html").forward(request, response);
                }

            }
        }
    }
}
