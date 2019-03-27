package com.codecool.servlet;

import com.codecool.database.PageList;
import com.codecool.database.UserList;
import com.codecool.model.curriculum.Page;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("attendance", UserList.getInstance().getUsers());
        request.getRequestDispatcher("attendance.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        recordAttendance(request);
        request.setAttribute("attendance", UserList.getInstance().getUsers());
        request.getRequestDispatcher("attendance.jsp").forward(request, response);

    }


    protected Date getAttendanceDate(HttpServletRequest request) throws ServletException, IOException{
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        Date date = null;
        try {
            date = myFormat.parse(request.getParameter("date"));
        } catch (Exception e) {
            e.printStackTrace();
        } return date;

    }

    protected void recordAttendance(HttpServletRequest request) throws ServletException, IOException{
        Date date = getAttendanceDate(request);
        String[] attendings = request.getParameterValues("attending");


        if (attendings != null) {
            for (User user : UserList.getInstance().getUsers()) {
                    if (user instanceof Student){
                        ((Student) user).setAttendance(date); }
            }
        }
    }



}
