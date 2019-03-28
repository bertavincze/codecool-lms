package com.codecool.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DatabaseAttendanceDao extends AbstractDao {

    public DatabaseAttendanceDao(Connection connection) {
        super(connection);
    }

    public void addAttendance(String attendance_id, String user_id, LocalDate attended_day, boolean is_present) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO attendance (attendance_id, user_id, attended_day, is_present) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, attendance_id);
            statement.setString(2, user_id);
            statement.setObject(3, attended_day);
            statement.setBoolean(4, is_present);
            executeInsert(statement);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }

    }

    public void updateAttendance(String user_id, LocalDate attended_day, boolean is_present) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "update attendance set is_present=? where user_id=? and attended_day=?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setBoolean(1, is_present);
            statement.setString(2, user_id);
            statement.setObject(3, attended_day);
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
