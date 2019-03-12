package com.codecool.filter;

import com.codecool.model.user.User;
import com.codecool.model.user.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/authenticate")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        System.out.println("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        System.out.println("Requested Resource:" + uri);

        HttpSession session = req.getSession(false);

        if (session == null && (uri.endsWith("student.html") || uri.endsWith("mentor.html"))) {
            System.out.println("Unauthorized access request");
            resp.sendRedirect("index.html");

        } else if (session != null && session.getAttribute("user") == null) {
            System.out.println("User not logged in");
            resp.sendRedirect("index.html");
            User currentUser = (User) req.getAttribute("user");

            if (currentUser != null) {
                if (currentUser.getUserRole().equals(UserRole.MENTOR)) {
                    request.getRequestDispatcher("mentor.html").forward(request, response);

                } else if (currentUser.getUserRole().equals(UserRole.STUDENT)) {
                    request.getRequestDispatcher("student.html").forward(request, response);
                }
            }

        } else {
            chain.doFilter(request, response);
        }
    }

}
