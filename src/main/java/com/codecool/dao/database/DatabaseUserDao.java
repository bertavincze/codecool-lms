package com.codecool.dao.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    public void addUser(String userID, String role, String name,  String email, String password) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO userBase (user_id, user_role, user_name, password, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
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
}
