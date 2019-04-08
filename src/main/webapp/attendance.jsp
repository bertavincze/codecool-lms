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
     <script>
            document.getElementById('datefield').valueAsDate = new Date();
     </script>
</head>

<body class="two_div">

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
                <input id="datefield" name="datefield" type="date" min="2019-03-01" max="2000-13-13">
                <script>
                    var today = new Date();
                    var dd = today.getDate();
                    var mm = today.getMonth()+1;
                    var yyyy = today.getFullYear();
                    if(dd<10){
                        dd='0'+dd
                    }
                    if(mm<10){
                        mm='0'+mm
                    }
                    var currentDate = yyyy+'-'+mm+'-'+dd;
                    document.getElementById("datefield").setAttribute("max", currentDate);
                    document.getElementById("datefield").value = currentDate;
                </script>
                <table>
                <tr>
                    <th>Name</th>
                    <th>Attendance rate</th>
                    <th>Current Attendance</th>
                </tr>
                <c:forEach var="u" items="${students}">
                    <tr>
                        <td>${u.getName()}</td>
                        <td>${u.getAttendanceRate()}%</td>
                        <td>
                            <label class="switch">
                            <input type="checkbox" name="attending" value="${u.getName()}">
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
