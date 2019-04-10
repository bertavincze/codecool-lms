package com.codecool.service.dao;

import com.codecool.dao.database.database.DatabaseAttendanceDao;
import com.codecool.model.user.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public class AttendanceService {

    private final DatabaseAttendanceDao attendanceDao;

    public AttendanceService(DatabaseAttendanceDao attendanceDao) {
        this.attendanceDao = attendanceDao;
    }


    public void addAttendance(String attendance_id, String user_id, LocalDate attended_day, boolean is_present) throws SQLException {

        try {
            attendanceDao.addAttendance(attendance_id, user_id, attended_day, is_present); // Insert data here);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateAttendance(String user_id, LocalDate attended_day, boolean is_present) throws SQLException {

        try {
            attendanceDao.updateAttendance(user_id, attended_day, is_present); // Insert data here);
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Map<LocalDate, Boolean> getAttendance(User user) throws SQLException{
        return attendanceDao.findAttendanceMapByUser(user);
    }
}
