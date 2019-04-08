package com.codecool.servlet;

import com.codecool.dao.database.DatabaseNewsDao;
import com.codecool.model.News;
import com.codecool.model.user.User;
import com.codecool.service.IDGeneratorService;
import com.codecool.service.NewsService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDate.now;

@WebServlet("/news")
public class NewsServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseNewsDao newsDao = new DatabaseNewsDao(connection);
            NewsService newsService = new NewsService(newsDao);
            User user = ((User) req.getSession().getAttribute("user"));


            List<News> getNews = newsService.getLatestNews();
            News latestNews = newsService.getNewestOne();

            req.setAttribute("Current", latestNews);
            req.setAttribute("Older", getNews);
            req.setAttribute("User", user);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } req.getRequestDispatcher("news.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = getConnection(req.getServletContext())) {
            DatabaseNewsDao newsDao = new DatabaseNewsDao(connection);
            NewsService newsService = new NewsService(newsDao);
            IDGeneratorService idService = new IDGeneratorService();

            String title = req.getParameter("title");
            String content = req.getParameter("content");
            String generatedID = idService.generateID();
            User user = ((User) req.getSession().getAttribute("user"));
            String userID = user.getId();
            LocalDateTime date = LocalDateTime.now();

            newsService.addNews(new News(generatedID, title, content, date, userID), user);

            List<News> getNews = newsService.getLatestNews();
            News latestNews = newsService.getNewestOne();

            req.setAttribute("Current", latestNews);
            req.setAttribute("Older", getNews);
            req.setAttribute("User", user);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        req.getRequestDispatcher("news.jsp").forward(req, resp);
    }
}
