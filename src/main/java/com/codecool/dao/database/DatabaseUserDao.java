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

    public List<Mentor> findMentors() throws SQLException {
        String sql = "SELECT user_id, user_name , email, password FROM users WHERE user_role='mentor'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Mentor> mentors = new ArrayList<>();
            while (resultSet.next()) {
                mentors.add((Mentor) fetchMentor(resultSet, "mentor"));
            }
            return mentors;
        }
    }

    public List<Student> findStudents() throws SQLException {
        String sql = "SELECT user_id, user_name , email, password FROM users WHERE user_role='student'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add((Student) fetchMentor(resultSet, "student"));
            }
            return students;
        }
    }

    public void addUser(String userID, String role, String name,  String email, String password) throws SQLException {
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
}
