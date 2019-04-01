package com.codecool.servlet;

import com.codecool.dao.database.AttendanceList;
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

@WebServlet("/setDate")
public class SetDateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        request.setAttribute("attendance", UserList.getInstance().getUsers());
        request.setAttribute("newDate", date);
        request.setAttribute("attendingStudent", AttendanceList.getInstance().getAttendanceInstance("date"));
        request.getRequestDispatcher("attendance.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Attendance requestedDay = getCheckedList(request);
        request.setAttribute("attendance", UserList.getInstance().getUsers());
        request.getRequestDispatcher("attendance").forward(request, response);


    }

    public Attendance getCheckedList(HttpServletRequest request){
        for(Attendance attendance : AttendanceList.getInstance().getAllTimeAttendanceList()){
            if (request.getParameter("date").equals(attendance.getDate())){
                return attendance;
            }
        }
        return null;
    }





}
