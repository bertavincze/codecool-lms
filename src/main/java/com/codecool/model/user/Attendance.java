package com.codecool.model.user;

import com.codecool.dao.database.UserList;
import com.sun.source.tree.Tree;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

public class Attendance implements Serializable {
    public LocalDate date = LocalDate.now();
    private TreeSet<Student> studentAttendance;

    public Attendance(LocalDate date, TreeSet<Student> studentAttendance){
        this.date = date;
        this.studentAttendance = studentAttendance;
    }

    public Attendance(LocalDate date, String[] names){
        this.date = date;
        setStudentAttendance(names);
    }

    public String getDate(){
        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        return (this.date).format(formatter_1);
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
