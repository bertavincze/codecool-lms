package com.codecool.model.user;


import com.codecool.model.curriculum.Solution;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends User implements Serializable {

    private List<Solution> solutionList;
    private ArrayList<Date> attendance = new ArrayList<>();


    public Student(String name, String email, String password) {
        super(name, email, password);
        solutionList = new ArrayList<>();
    }

    public void addSolution(Solution solution) {
        if (!solutionList.contains(solution)) {
            solutionList.add(solution);
        }
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }

    public void setAttendance(Date date){
        attendance.add(date);
    }

    public int getAttendanceRate(){
        if (attendance.isEmpty()){
            return 0;
        } else {
            return (int) ((attendance.size()/(new DateCounter().getDaysDifference()))*100);
        }
    }

    public ArrayList<String> getAttendance(){
        ArrayList<String> attendanceList = new ArrayList<>();
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        for(Date date : attendance){
            String stringDate = myFormat.format(date);
            attendanceList.add(stringDate);
        } return attendanceList;
    }
    

}

