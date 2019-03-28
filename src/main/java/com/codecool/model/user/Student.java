package com.codecool.model.user;

import com.codecool.model.curriculum.Solution;
import com.codecool.service.DateUtilService;

import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;

public class Student extends User implements Serializable {

    private List<Solution> solutionList;
    private Map<LocalDate, Boolean> attendance;

    public Student(String id, String name, String email, String password) {
        super(id, name, email, password);
        solutionList = new ArrayList<>();
        attendance = new HashMap<>();
    }

    public void addSolution(Solution solution) {
        if (!solutionList.contains(solution)) {
            solutionList.add(solution);
        }
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }

    public void setAttendance(LocalDate date, boolean isPresent) {
        attendance.put(date, isPresent);
    }

    public int getAttendanceRate() {
        if (attendance.isEmpty()) {
            return 0;
        } else {
            int daysAttended = 0;
            for (Map.Entry<LocalDate, Boolean> entry : attendance.entrySet()) {
                if (entry.getValue()) {
                    daysAttended ++;
                }
            }
            float totalDays = ((float)new DateUtilService().getNumOfDaysSinceStart());
            return (int) ((daysAttended/(totalDays))*100);
        }
    }

    public Map<LocalDate, Boolean> getAttendance() {
        return attendance;
    }

}

