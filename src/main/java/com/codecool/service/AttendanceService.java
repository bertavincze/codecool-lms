package com.codecool.service;

import com.codecool.dao.database.DatabaseAttendanceDao;

import java.sql.SQLException;
import java.time.LocalDate;

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
}
