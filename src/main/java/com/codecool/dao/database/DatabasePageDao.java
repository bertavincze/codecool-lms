package com.codecool.dao.database;

import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.curriculum.TextPage;
import com.codecool.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePageDao extends AbstractDao {

    public DatabasePageDao(Connection connection) {
        super(connection);
    }

    public void addPage(Page page) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO page (page_id, title, isPublished) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, page.getId());
            statement.setString(2, page.getTitle());
            statement.setBoolean(3, page.isPublished());
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }

        if (page instanceof AssignmentPage) {
            connection.setAutoCommit(false);
            sql = "INSERT INTO assignment_page (page_id, question, max_score) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, page.getId());
                statement.setString(2, ((AssignmentPage) page).getQuestion());
                statement.setInt(3, ((AssignmentPage) page).getMaxScore());
                executeInsert(statement);
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(autoCommit);
            }

        } else if (page instanceof TextPage) {
            connection.setAutoCommit(false);
            sql = "INSERT INTO text_page (page_id, content) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, page.getId());
                statement.setString(2, ((TextPage) page).getContent());
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

    public void updatePagePublishedState(Page page, boolean isPublished) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "UPDATE page SET isPublished=? WHERE page_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setBoolean(1, isPublished);
            statement.setString(2, page.getId());
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public List<Page> loadAllPages() throws SQLException {
        List<Page> allPages = new ArrayList<>();
        allPages.addAll(loadAssignmentPages());
        allPages.addAll(loadTextPages());
        return allPages;
    }

    public List<AssignmentPage> loadAssignmentPages() throws SQLException {
        List<AssignmentPage> assignments = new ArrayList<>();
        String sql = "SELECT page.page_id, title, ispublished, question, max_score FROM page " +
            "JOIN assignment_page ON page.page_id = assignment_page.page_id";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                AssignmentPage page = new AssignmentPage(
                    resultSet.getString("page_id"),
                    resultSet.getString("title"),
                    resultSet.getString("question"),
                    resultSet.getInt("max_score"));
                if (resultSet.getBoolean("ispublished")) {
                    page.publish();
                } else {
                    page.unpublish();
                }
                assignments.add(page);
            }
        }
        return assignments;
    }

    public List<TextPage> loadTextPages() throws SQLException {
        List<TextPage> textPages = new ArrayList<>();
        String sql = "SELECT page.page_id, title, ispublished, content FROM page JOIN text_page ON page.page_id = text_page.page_id";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                TextPage page = new TextPage(
                    resultSet.getString("page_id"),
                    resultSet.getString("title"),
                    resultSet.getString("content"));
                if (resultSet.getBoolean("ispublished")) {
                    page.publish();
                } else {
                    page.unpublish();
                }
                textPages.add(page);
            }
        }
        return textPages;
    }

    public void addToSolutionMap(Solution solution, User user, Page page) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO solutionmap (user_id, solution_id, page_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getId());
            statement.setString(2, solution.getSolution_id());
            statement.setString(3, page.getId());
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }



    /*NEWLY ADDED UPDATE CODE*/
    public void updatePage(Page page, String title, String content) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        String sql = "UPDATE page SET title=? WHERE page_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, title);
            statement.setString(2, page.getId());
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }

        if (page instanceof AssignmentPage) {
            connection.setAutoCommit(false);
            sql = "UPDATE assignment_page SET question=? WHERE page_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, content);
                statement.setString(2, page.getId());
                executeInsert(statement);
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(autoCommit);
            }

        } else if (page instanceof TextPage) {
            connection.setAutoCommit(false);
            sql = "UPDATE text_page SET content=? WHERE page_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, content);
                statement.setString(2,  page.getId());
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

    public void removePage(String id) throws SQLException{
            String sql = "DELETE FROM page WHERE page_id=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id);
                statement.executeUpdate();
            } catch (SQLException ex) {
                throw ex;
            }
    }
}
