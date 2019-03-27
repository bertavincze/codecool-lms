package com.codecool.model.user;


import com.codecool.model.curriculum.Solution;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

public class Student extends User implements Serializable {

    private List<Solution> solutionList;
    private TreeSet<LocalDate> attendance;


    public Student(String id, String name, String email, String password) {
        super(id, name, email, password);
        solutionList = new ArrayList<>();
        attendance = new TreeSet<>();
    }

    public void addSolution(Solution solution) {
        if (!solutionList.contains(solution)) {
            solutionList.add(solution);
        }
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }

    public void setAttendance(LocalDate date) {
        attendance.add(date);
    }

    public int getAttendanceRate() {
        if (attendance.isEmpty()) {
            return 0;
        } else {
            float totalDays = ((float)new DateCounter().getDifferenceInDays());
            return (int) ((attendance.size()/(totalDays))*100);
        }
    }

    public ArrayList<String> getAttendance(){
        ArrayList<String> attendanceList = new ArrayList<>();
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        for(LocalDate date : attendance){
            String stringDate = myFormat.format(date);
            attendanceList.add(stringDate);
        } return attendanceList;
    }

    public TreeSet<LocalDate> getAttendanceDates(){
        return attendance;
    }


}

