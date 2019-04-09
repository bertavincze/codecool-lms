<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoatCool</title>
    <link rel="stylesheet" type="text/css" href="resources/css/finalstyle.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto|Megrim' rel='stylesheet' type='text/css'>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body class="two_div">
<script>
    $( document ).ready(function() {
        $(function() {
             $('#datepicker').datepicker().datepicker('setDate', new Date(Date.parse("${dateFromRequest}")));
             $('#datepicker').datepicker( "option", "maxDate", new Date());
             $('#datepicker').attr("autocomplete", "off");
        });

        $("#datepicker").datepicker({
        onSelect: function (date) {
            window.location.href = "attendance?date=" + date;
            }
        });

    });
</script>

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li>Curriculum</li></a>
        <a href="attendance"><li class="marked">Attendance</li></a>
        <a href="solutions"><li>Student Solutions</li></a>
    </ul>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Attendance</div>
            </div>
            <form action="attendance" method="post">
                <input type="text" id="datepicker" name="date"></input>
                <table>
                <tr>
                    <th>Name</th>
                    <th>Attendance rate</th>
                    <th>Current Attendance</th>
                </tr>
                <c:set var="date" scope="session" value="${date}" />
                <c:forEach var="u" items="${students}">
                    <tr>
                        <td>${u.getName()}</td>
                        <td>${u.getAttendanceRate()}%</td>
                        <td>
                            <label class="switch">
                            <c:choose>
                            <c:when test="${u.getAttendance().get(date)}">
                            <input type="checkbox" name="attending" value="${u.getName()}" checked>
                            </c:when>
                            <c:otherwise>
                            <input type="checkbox" name="attending" value="${u.getName()}">
                            </c:otherwise>
                            </c:choose>
                            <span class="slider"></span>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
                </table>
                <p align="center">Check the box to add attendance record for the selected date.</p>
                <center><input class="button" type="submit" value="Set attendance"></center>
                <br>
            </form>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
