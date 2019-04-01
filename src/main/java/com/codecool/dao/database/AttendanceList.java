package com.codecool.dao.database;

import com.codecool.model.user.Attendance;
import com.codecool.model.user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceList implements Serializable {
    private static AttendanceList ourInstance = new AttendanceList();
    private List<Attendance> allTimeAttendanceList;

    public static AttendanceList getInstance() {
        return ourInstance;
    }

    private AttendanceList() {
        allTimeAttendanceList = new ArrayList<>();
    }

    public void addAttendance(Attendance attendance) {
        allTimeAttendanceList.add(attendance);
    }

    public List<Attendance> getAllTimeAttendanceList() {
        return allTimeAttendanceList;
    }

    public List<Attendance> getAttendanceInstance(String date){
        return allTimeAttendanceList;
    }


}
