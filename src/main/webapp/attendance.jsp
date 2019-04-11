<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <script src="javascript/themeChanger.js">
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoatCool</title>
    <link rel="stylesheet" type="text/css" href="resources/css/finalstyle.css" id="themer">
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Roboto|Megrim' rel='stylesheet' type='text/css'>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>


<body class="two_div" onload="checkCookie()">
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
         <jsp:include page="snippets/themeChanger.jsp"/>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Attendance</div>
            </div>
<<<<<<< HEAD
            <form method="post" action="setDate">
                <input id="setNewDate" type="submit" value="Set date">
                <input id="datefield2" type='date' min='1899-01-01' max='2000-13-13'/>

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
                    document.getElementById("datefield2").setAttribute("max", currentDate);
                </script>
            </form>
            <form method="post" action="attendance">
                <input id="datefield" type='date' min='1899-01-01' max='2000-13-13'/>
                <script>
                    document.getElementById("datefield").setAttribute("max", currentDate);
                    document.getElementById("datefield").value = currentDate;
                </script>
=======
            <form action="attendance" method="post">
                <input type="text" id="datepicker" name="date"></input>
>>>>>>> master
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
<<<<<<< HEAD
                <input type="submit" value="Set attendance">
=======
                <center><input class="button" type="submit" value="Set attendance"></center>
>>>>>>> master
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
