<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
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
        <a href="curriculum"><li class="marked">Curriculum</li></a>
        <a href="assignments"><li>Assignments</li></a>
        <a href="stats"><li>Stats</li></a>
    </ul>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Curriculum</div>
            </div>
            <h2>Assignment Pages</h2>
            <ul>
            <c:forEach var="page" items="${pageList}">
                 <c:choose>
                 <c:when test="${page.isPublished() && page.getClass().simpleName == 'AssignmentPage'}">
                        <a href="handlepage?title=${page.getTitle()}"><li>${page.getTitle()}</li></a>
                  </c:when>
            </c:choose>
            </c:forEach>
            </ul>
            <br>
            <h2>Text Pages</h2>
            <ul>
            <c:forEach var="page" items="${pageList}">
                 <c:choose>
                 <c:when test="${page.isPublished() && page.getClass().simpleName == 'TextPage'}">
                        <a href="handlepage?title=${page.getTitle()}"><li>${page.getTitle()}</li></a>
                  </c:when>
            </c:choose>
            </c:forEach>
            </ul>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">
            <div class="containerhead">
                <div class="title">Logged in user</div>
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
                <form action="logout" method="post">
                    <input type="submit" value="Log out">
                </form>
                <br></div>

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
