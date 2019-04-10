package com.codecool.service.dao;


import com.codecool.dao.database.database.DatabaseNewsDao;
import com.codecool.model.News;
import com.codecool.model.user.User;

import java.sql.SQLException;
import java.util.List;

public class NewsService {
    private final DatabaseNewsDao newsDao;

    public NewsService(DatabaseNewsDao newsDao) {
        this.newsDao = newsDao;
    }


    public void addNews(News news, User user) throws SQLException {
        newsDao.addNews(news, user);
    }


    public List<News> getLatestNews() throws SQLException {
        return newsDao.getLatestNine();
    }

    public News getNewestOne() throws SQLException{
        return newsDao.getLatest();
    }

    public List<News> getAll() throws SQLException {
        return newsDao.getAll();
    }
}
