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
        String sqlMentor = "SELECT user_id, user_name, email, password, image_id FROM userBase WHERE user_role='mentor'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlMentor)) {
            while (resultSet.next()) {
                users.add((Mentor) fetchMentor(resultSet, "mentor"));
            }
        }

        String sqlStudent = "SELECT user_id, user_name, email, password, image_id FROM userBase WHERE user_role='student'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlStudent)) {
            while (resultSet.next()) {
                users.add((Student) fetchMentor(resultSet, "student"));
            }
        }
        return users;
    }

    public void addUser(String userID, String role, String name, String email, String password, String image_id) throws SQLException {

        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO userBase (user_id, user_role, user_name, email, password, image_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, userID);
            statement.setString(2, role);
            statement.setString(3, name);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setString(6, image_id);
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
        String image_id = resultSet.getString("image_id");
        if (type.equals("mentor")) {
            return new Mentor(id, name, email, password, image_id);
        } else if (type.equals("student")) {
            return new Student(id, name, email, password, image_id);
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

    public void changeProfilePic(String user_id, String image_id) throws SQLException{
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update userBase set image_id=? where user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, image_id);
            statement.setString(2, user_id);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }


    public void removeUser(String id) throws SQLException{
        String sql = "DELETE FROM userbase WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }

}
