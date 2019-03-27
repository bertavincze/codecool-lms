package com.codecool.dao.database;

import com.codecool.model.curriculum.AssignmentPage;
import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.TextPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePageDao extends AbstractDao {

    DatabasePageDao(Connection connection) {
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
}
