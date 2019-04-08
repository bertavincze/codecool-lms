<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoatCool</title>
    <link rel="stylesheet" type="text/css" href="resources/css/finalstyle.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto|Megrim' rel='stylesheet' type='text/css'>
</head>

<body class="two_div">

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li class="marked">UserList</li></a>
        <a href="curriculum"><li>Curriculum</li></a>
        <c:choose>
            <c:when test="${user.getClass().simpleName == 'Student'}">
                 <a href="assignments"><li>Assignments</li></a>
                 <a href="stats"><li>Stats</li></a>
            </c:when>
            <c:otherwise>
                <a href="attendance"><li>Attendance</li></a>
                <a href="solutions"><li>Student Solutions</li></a>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">Registered Users</a></div>
            </div>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>E-mail</th>
                        <th>Role</th>
                    </tr>
                    <c:forEach var="u" items="${userList}">
                        <tr>
                            <td><c:out value="${u.name}"/></td>
                            <td><c:out value="${u.email}"/></td>
                            <c:choose>
                                  <c:when test="${u.getClass().simpleName == 'Student'}">
                                        <td>Student</td>
                                  </c:when>
                                  <c:otherwise>
                                        <td>Mentor</td>
                                  </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
    <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
