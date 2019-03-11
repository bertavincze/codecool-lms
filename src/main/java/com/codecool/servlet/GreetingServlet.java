package com.codecool.servlet;

import com.codecool.model.Greeting;
import com.codecool.service.GreetingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/greeting")
public class GreetingServlet extends HttpServlet {

    private final GreetingService service = new GreetingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Greeting> greetings = service.getGreetings();
        req.setAttribute("greetings", greetings);

        boolean jstl = Boolean.valueOf(req.getParameter("jstl"));
        if (jstl) {
            req.getRequestDispatcher("greeting-jstl.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("greeting.jsp").forward(req, resp);
        }
    }
}
