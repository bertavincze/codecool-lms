package com.codecool.filter;

import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/fuck")
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

        if (session == null && !uri.endsWith("index.html") && !uri.endsWith("login")) {
            System.out.println("Unauthorized access request");
            resp.sendRedirect("index.html");

        } else if (session != null && session.getAttribute("user") == null) {
            System.out.println("User not logged in");
            resp.sendRedirect("index.html");

        } else if (session != null && session.getAttribute("user") != null) {
            User currentUser = (User) req.getAttribute("user");

            if (currentUser instanceof Student) {
                for (String url: ((Student) currentUser).getUrlList()) {
                    if (uri.endsWith(url)) {
                        chain.doFilter(request, response);
                    } else {
                        req.getRequestDispatcher("noauth.jsp");
                    }
                }

            } else if (currentUser instanceof Mentor) {
                for (String url: ((Mentor) currentUser).getUrlList()) {
                    if (uri.endsWith(url)) {
                        chain.doFilter(request, response);
                    } else {
                        req.getRequestDispatcher("noauth.jsp");
                    }
                }
            }
        }
    }

}
