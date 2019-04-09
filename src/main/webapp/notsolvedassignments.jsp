<%@ page contentType="text/html; charset=UTF-8" %><%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li>Curriculum</li></a>
        <c:choose>
            <c:when test="${user.getClass().simpleName == 'Student'}">
                 <a href="assignments"><li class="marked">Assignments</li></a>
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
                <div class="title"><a href="">Unsolved Assignments</a></div>
            </div>
            <c:if test="${fn:length(assignments) == 0}">
                  <p>No unsolved assignments found.</p>
            </c:if>
            <ul>
            <c:forEach var="a" items="${assignments}">
                <a href="handlepage?title=${a.getTitle()}"><li>${a.getTitle()}</li></a>
            </c:forEach>
            </ul>
            <div class="containerfoot"></div>
        </div>
    </div>


    <div class="sidebar">
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
