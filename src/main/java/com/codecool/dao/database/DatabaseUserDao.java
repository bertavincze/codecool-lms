package com.codecool.dao.database;

import com.codecool.model.user.Mentor;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    public List<User> findUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sqlMentor = "SELECT user_id, user_name, email, password FROM users WHERE user_role='mentor'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlMentor)) {
            while (resultSet.next()) {
                users.add((Mentor) fetchMentor(resultSet, "mentor"));
            }
        }

        String sqlStudent = "SELECT user_id, user_name, email, password FROM users WHERE user_role='student'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlStudent)) {
            while (resultSet.next()) {
                users.add((Student) fetchMentor(resultSet, "student"));
            }
        }
        return users;
    }

    public void addUser(String userID, String role, String name, String email, String password) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO userBase (user_id, user_role, user_name, email, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userID);
            statement.setString(2, role);
            statement.setString(3, name);
            statement.setString(4, email);
            statement.setString(5, password);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    private User fetchMentor(ResultSet resultSet, String type) throws SQLException {
        String id = resultSet.getString("user_id");
        String name = resultSet.getString("user_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        if (type.equals("mentor")) {
            return new Mentor(id, name, email, password);
        } else if (type.equals("student")) {
            return new Student(id, name, email, password);
        }
        return null; // it could be a problem later I'm leaving this here till I test it
    }


    public void updateName(String user_id, String name) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update userBase set user_name=? where user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, user_id);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex; //??
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void updatePassword(String user_id, String password) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update userBase set password=? where user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, password);
            statement.setString(2, user_id);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex; //??
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    public void updateEmail(String user_id, String email) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update userBase set email=? where user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, email);
            statement.setString(2, user_id);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex; //??
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }
}
