package com.codecool.servlet;

import com.codecool.database.PageList;
import com.codecool.model.curriculum.Page;
import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/publishservlet")
public class PublishServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String[] published = request.getParameterValues("publish");
        String[] unpublished = request.getParameterValues("unpublish");

        //needs refactoring
        if (published != null) {
            for (Page page : PageList.getInstance().getPageList()) {
                for (String name : published) {
                    System.out.println(name);
                    if (page.getTitle().equals(name)) {
                        page.publish();
                    }
                }
            }
        }

        if (unpublished != null) {
            for (Page page : PageList.getInstance().getPageList()) {
                for (String name : unpublished) {
                    if (page.getTitle().equals(name)) {
                        page.unpublish();
                    }
                }
            }
        }
        request.getRequestDispatcher("curriculum").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("curriculum").forward(request, response);

    }
}


