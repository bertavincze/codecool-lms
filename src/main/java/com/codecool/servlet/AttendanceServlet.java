package com.codecool.servlet;

import com.codecool.dao.database.database.DatabaseAttendanceDao;
import com.codecool.dao.database.database.DatabaseUserDao;
import com.codecool.model.user.Student;
import com.codecool.model.user.User;
import com.codecool.service.dao.AttendanceService;
import com.codecool.service.dao.IDGeneratorService;
import com.codecool.service.dao.UserService;
import com.codecool.service.servlet.UserUtilService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/attendance")
public class AttendanceServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            UserService userService = new UserService(userDao, attendanceDao);

            List<User> students = userService.getUsersWithMap();
            String dateFromRequest = request.getParameter("date");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate localDate;
            if (dateFromRequest != null) {
                localDate = LocalDate.parse(dateFromRequest, dtf);
            } else {
                localDate = LocalDate.now();
            }

            request.setAttribute("dateFromRequest", dateFromRequest);
            request.setAttribute("date", localDate);
            request.setAttribute("students", students);
            request.getRequestDispatcher("attendance.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            DatabaseAttendanceDao attendanceDao = new DatabaseAttendanceDao(connection);
            AttendanceService attendanceService = new AttendanceService(attendanceDao);
            DatabaseUserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new UserService(userDao, attendanceDao);
            IDGeneratorService idGeneratorService = new IDGeneratorService();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate localDate = LocalDate.parse(request.getParameter("date"), dtf);

            List<String> attending = Arrays.asList(request.getParameterValues("attending"));

            for (String name : attending) {
                Student student = UserUtilService.findStudentByName(name, userService.getUsers());
                if (student != null) {
                    student.setAttendance(localDate, true);
                    attendanceService.addAttendance(idGeneratorService.generateID(), student.getId(), localDate, student.getAttendance().get(localDate));
                }
            }

            for (User user : userService.getUsers()) {
                if (user instanceof Student) {
                    if (!attending.contains(user.getName())) {
                        ((Student) user).setAttendance(localDate, false);
                        attendanceService.addAttendance(idGeneratorService.generateID(), user.getId(), localDate, ((Student) user).getAttendance().get(localDate));
                    }
                }
            }

            List<User> students = userService.getUsersWithMap();

            request.setAttribute("date", localDate);
            request.setAttribute("students", students);
            request.getRequestDispatcher("attendance.jsp").forward(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


}
