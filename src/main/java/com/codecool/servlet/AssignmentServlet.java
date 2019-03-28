package com.codecool.servlet;

import com.codecool.dao.database.PageList;
import com.codecool.dao.database.UserList;
import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/assignments")
public class AssignmentServlet extends HttpServlet {
    List<Solution> solutions;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Student user = (Student) session.getAttribute("user");
        solutions = user.getSolutionList();
        List<AssignmentPage> assignments = findAssignmentsNotSolved();

        request.setAttribute("assignments", assignments);
        request.getRequestDispatcher("notsolvedassignments.jsp").forward(request, response);
    }

    private List<AssignmentPage> findAssignmentsNotSolved() {
        List<String> titleList = new ArrayList<>();
        List<AssignmentPage> assignments = new ArrayList<>();
        if (solutions == null) {
            for (Page page : PageList.getInstance().getPageList()) {
                if (page instanceof AssignmentPage) {
                        assignments.add((AssignmentPage)page);
                    }
                }

        } else {
            for (Solution solution : solutions) {
                titleList.add(solution.getTitle());
            }
            for (Page page : PageList.getInstance().getPageList()) {
                if (page instanceof AssignmentPage) {
                    if (!titleList.contains(((AssignmentPage)page).getTitle())) {
                        assignments.add((AssignmentPage)page);
                    }
                }
            }
        }

        return assignments;
    }
}
