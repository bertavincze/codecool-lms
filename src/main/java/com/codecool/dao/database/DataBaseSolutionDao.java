package com.codecool.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class DataBaseSolutionDao extends AbstractDao {

    public DataBaseSolutionDao(Connection connection) {
        super(connection);
    }

    public void addSolution(String solution_id, String userID, String title, String answer, LocalDateTime time) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO userBase (solution_id, userID, title, answer, submission_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, solution_id);
            statement.setString(2, userID);
            statement.setString(3, title);
            statement.setString(4, answer);
            statement.setObject(5, time);
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
