package com.codecool.model.user;

import com.codecool.dao.database.UserList;
import com.sun.source.tree.Tree;

import java.time.LocalDate;
import java.util.TreeSet;

public class Attendance {
    private LocalDate date;
    private TreeSet<Student> studentAttendance;

    public Attendance(LocalDate date, TreeSet<Student> studentAttendance){
        this.date = date;
        this.studentAttendance = studentAttendance;
    }

    public Attendance(LocalDate date, String[] names){
        this.date = date;
        setStudentAttendance(names);
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public TreeSet<Student> getStudentAttendance(){
        return studentAttendance;
    }

    public void setStudentAttendance(String[] names){
        for(User user : UserList.getInstance().getUsers()){
            for(String name : names){
                if(name.equals(user.getName())){
                    studentAttendance.add((Student) user);
                }
            }
        }
    }

}
