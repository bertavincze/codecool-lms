package com.codecool.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
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

        if (session == null && !(uri.endsWith("index.html") || !uri.endsWith("registration.html"))){
            System.out.println("Unauthorized access request");
            req.getRequestDispatcher("index.html").forward(req, resp);
        }else{
            chain.doFilter(request, response);
        }
    }

}
