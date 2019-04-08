package com.codecool.dao.database;

import com.codecool.model.News;
import com.codecool.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseNewsDao extends AbstractDao {
    public DatabaseNewsDao(Connection connection) {
        super(connection);
    }

    public List<News> getAll() throws SQLException {
        List<News> allNews = new ArrayList<>();
        String sql ="SELECT * FROM newsfeed ORDER BY date";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                allNews.add(new News(resultSet.getString("news_id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getDate("date").toLocalDate()));
            }
        return allNews;
        }
    }

    public List<News> getLatestNine() throws SQLException {
        List<News> latestNews = new ArrayList<>();
        String sql ="SELECT * FROM newsfeed ORDER BY date DESC LIMIT 9 OFFSET 1";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                latestNews.add(new News(resultSet.getString("news_id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getDate("date").toLocalDate()));
            }
            return latestNews;
        }
    }

    public News getLatest() throws SQLException{
        News latestNews;
        String sql = "SELECT news_id, title, content, date FROM newsfeed a " +
            "RIGHT OUTER JOIN (SELECT MAX(date) AS latest FROM newsfeed)b " +
            "ON a.date = b.latest;";
        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                latestNews = new News(resultSet.getString("news_id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getDate("date").toLocalDate());
                return latestNews;
            }
        } return null;
    }

    public void addNews(News news, User user) throws SQLException{
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO newsfeed (news_id, title, content, date, user_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, news.getId());
            statement.setString(2, news.getTitle());
            statement.setString(3, news.getContent());
            statement.setDate(4, Date.valueOf(news.getDate()));
            statement.setString(5, user.getId());
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }



}
