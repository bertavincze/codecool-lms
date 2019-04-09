package com.codecool.servlet;

import com.codecool.dao.database.DatabaseImageDao;
import com.codecool.dao.database.DatabaseNewsDao;
import com.codecool.dao.database.DatabaseUserDao;
import com.codecool.model.Image;
import com.codecool.model.News;
import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;
import com.codecool.service.NewsService;
import com.codecool.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = authUser(request);
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabaseNewsDao newsDao = new DatabaseNewsDao(connection);
            NewsService newsService = new NewsService(newsDao);

            List<News> getNews = newsService.getLatestNews();
            News latestNews = newsService.getNewestOne();

            request.setAttribute("Current", latestNews);
            request.setAttribute("Older", getNews);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        routeUser(request, response, currentUser);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        uploadDefaultImagesOnLoad(request);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


    private User authUser(HttpServletRequest request) {
        User currentUser = null;
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new UserService(userDao);

            String name = request.getParameter("name");
            String password = request.getParameter("password");

            for (User user : userService.getUsers()) {
                if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                    currentUser = user;
                    HttpSession session = request.getSession();
                    session.setAttribute("user", currentUser);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return currentUser;
    }

    private void routeUser(HttpServletRequest request, HttpServletResponse response, User currentUser) throws ServletException, IOException {
        if (currentUser != null) {
            if (currentUser instanceof Mentor || currentUser instanceof Student) {
                request.getRequestDispatcher("news.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }


    }

    protected void uploadDefaultImagesOnLoad(HttpServletRequest request) throws IOException{
        try(Connection connection = getConnection(request.getServletContext())) {
            DatabaseImageDao imageDao = new DatabaseImageDao(connection);
            imageDao.updateImage(new Image("1", "/home/tamy/Documents/Java/codecool-lms/src/main/webapp/resources/pics/profile.jpg", "default"));
            imageDao.addImage(new Image("2", "/home/tamy/Documents/Java/codecool-lms/src/main/webapp/resources/pics/pic2.jpg", "test"));
            imageDao.addImage(new Image("3", "/home/tamy/Documents/Java/codecool-lms/src/main/webapp/resources/pics/pic3.jpg", "test"));
            imageDao.addImage(new Image("4", "/home/tamy/Documents/Java/codecool-lms/src/main/webapp/resources/pics/pic4.jpg", "test"));
            imageDao.addImage(new Image("5", "/home/tamy/Documents/Java/codecool-lms/src/main/webapp/resources/pics/pic5.jpg", "test"));
            imageDao.addImage(new Image("6", "/home/tamy/Documents/Java/codecool-lms/src/main/webapp/resources/pics/pic6.png", "test"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
