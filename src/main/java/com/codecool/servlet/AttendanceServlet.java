package com.codecool.servlet;

import com.codecool.dao.database.UserList;

import com.codecool.model.user.Attendance;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {

    Attendance attendanceInstance;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("attendance", UserList.getInstance().getUsers());
        request.getRequestDispatcher("attendance.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        recordAttendance(request);
        createAttendance(request);
        request.setAttribute("attendance", UserList.getInstance().getUsers());
        request.getRequestDispatcher("attendance.jsp").forward(request, response);

    }

    private LocalDate getAttendanceDate(HttpServletRequest request) throws ServletException, IOException{
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        LocalDate date = null;
        try {
            date = convertToLocalDateViaInstant(myFormat.parse(request.getParameter("date")));
        } catch (Exception e) {
            e.printStackTrace();
        } return date;

    }

    protected void recordAttendance(HttpServletRequest request) throws ServletException, IOException{
        LocalDate date = getAttendanceDate(request);
        String[] attendings = request.getParameterValues("attending");


        if (attendings != null) {
            for (User user : UserList.getInstance().getUsers()) {
                if (user instanceof Student){
                    for (String nameList : attendings){
                        if (nameList.equals(user.getName())){
                            ((Student)user).setAttendance(date);
                        }
                    }
                }

            }
        }
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    private void createAttendance(HttpServletRequest request){
        LocalDate date;
        try {
            date = getAttendanceDate(request);
            String[] attendings = request.getParameterValues("attending");
            attendanceInstance = new Attendance(date, attendings);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


    /*protected LocalDate getAttendanceDate(HttpServletRequest request) throws ServletException, IOException{
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        LocalDate date = null;
        try {
            date = convertToLocalDateViaInstant(myFormat.parse(request.getParameter("date")));
        } catch (Exception e) {
            e.printStackTrace();
        } return date;

    }

    protected void recordAttendance(HttpServletRequest request) throws ServletException, IOException{
        LocalDate date = getAttendanceDate(request);
        String[] attendings = request.getParameterValues("attending");


        if (attendings != null) {
            for (User user : UserList.getInstance().getUsers()) {
                    if (user instanceof Student){
                        for (String nameList : attendings){
                            if (nameList.equals(user.getName())){
                                for (LocalDate attendanceDates : ((Student) user).getAttendanceDates()){
                                    if (!((Student) user).getAttendanceDates().contains(attendanceDates)){
                                        ((Student)user).setAttendance(date);
                                    }
                            }
                        }
            }
        }

            }
        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }*/


 /*if (attendings != null) {
            for (String user : attendings) {
                for (User u : UserList.getInstance().getUsers()) {
                    if (u instanceof Student && user.equals(u.getName())){
                        for (LocalDate attendanceDates : ((Student) u).getAttendanceDates()){
                            if (!date.equals(attendanceDates)){
                                ((Student)u).setAttendance(date);
                            }
                        }
                    }
                }
            }
        }*/
