package com.codecool.dao.database;

import com.codecool.model.curriculum.Page;
import com.codecool.model.curriculum.Solution;
import com.codecool.model.user.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSolutionDao extends AbstractDao {

    public DatabaseSolutionDao(Connection connection) {
        super(connection);
    }

    public void addSolution(String solution_id, String user_id, String title, String answer, LocalDateTime time) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO solution (solution_id, user_id, title, answer, submission_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, solution_id);
            statement.setString(2, user_id);
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

    public void updateSolution(String solution_id, int currGrade) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update solution set grade=? where solution_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setInt(1, currGrade);
            statement.setString(2, solution_id);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public List<Solution> loadSolutionsByPage(Page page) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "SELECT * FROM solution WHERE solution.title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, page.getTitle());
            connection.commit();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution(
                    resultSet.getString("solution_id"),
                    resultSet.getString("user_id"),
                    resultSet.getString("title"),
                    resultSet.getString("answer"),
                    localDateFromTimestamp(resultSet.getTimestamp("submission_date")));
                if (resultSet.getInt("grade") != 0) {
                    solution.setGrade(resultSet.getInt("grade"));
                }
                solutions.add(solution);
            }
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
        return solutions;
    }

    public List<Solution> loadSolutionsForSingleUser(User user) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "SELECT * FROM solution WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getId());
            connection.commit();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution(
                    resultSet.getString("solution_id"),
                    resultSet.getString("user_id"),
                    resultSet.getString("title"),
                    resultSet.getString("answer"),
                    localDateFromTimestamp(resultSet.getTimestamp("submission_date")));
                if (resultSet.getInt("grade") != 0) {
                    solution.setGrade(resultSet.getInt("grade"));
                }
                solutions.add(solution);
            }
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
        return solutions;
    }



    private LocalDateTime localDateFromTimestamp(Timestamp timestamp) {
        return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.ofHours(0));
    }

}
