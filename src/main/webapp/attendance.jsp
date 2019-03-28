<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Aladin|Merienda+One|Walter+Turncoat" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/css/text.css">
    <title>GoatCool</title>
    <script>
        document.getElementById('datefield').valueAsDate = new Date();
    </script>
</head>
<body>

<div class="header">
    <a href="index.html"><h1>GoatCool</h1></a>
</div>

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li>Curriculum</li></a>
        <a href="attendance"><li class="marked">Attendance</li></a>
        <a href="solutions"><li>Student Solutions</li></a>
    </ul>
</nav>


<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Attendance</div>
            </div>
            <h2>Attendance: </h2>
            <form action="attendance" method="post">
                <input id="datefield" name="datefield" type="date" max="2000-13-13">
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
                <p>Check the box to add attendance record for the selected date.</p>
                <table>
                <tr>
                    <td><p>Name</p></td>
                    <td><p>Attendance rate</p></td>
                    <td><p>Current Attendance</p></td>
                </tr>
                <c:forEach var="u" items="${students}">
                    <tr>
                        <td><p>${u.getName()}</p></td>
                        <td><p>${u.getAttendanceRate()}%</p></td>
                        <td><p><input type="checkbox" name="attending" value="${u.getName()}"><p></td>
                    </tr>
                </c:forEach>
                </table>
                <br>
                <input type="submit" value="Set attendance">
                <br>
            </form>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">
            <div class="containerhead">
                <div class="title">Current user</div>
            </div>

            <div class="desc">
                <p>Name: ${user.getName()}</p>
                    <c:choose>
                          <c:when test="${user.getClass().simpleName == 'Student'}">
                                <p>Role: Student</p>
                          </c:when>
                          <c:otherwise>
                                <p>Role: Mentor</p>
                          </c:otherwise>
                    </c:choose>
                <br>
            </div>

            <ul class="links">
                <div class="linktitle">Favourites</div>
                <li><a href="">Sidebar link 1</a></li>
                <li><a href="">Sidebar link 2</a></li>
                <li><a href="">Sidebar link 3</a></li>
                <li><a href="">Sidebar link 4</a></li>
            </ul>

        </div>
    </div>

</div>
</body>
</html>
