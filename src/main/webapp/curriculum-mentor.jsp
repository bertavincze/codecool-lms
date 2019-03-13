<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <title>GoatCool</title>
</head>
<body>

<div class="header">
    <a href="index.html"><h1>GoatCool</h1></a>
</div>

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li class="marked">Curriculum</li></a>
        <a href="attendance"><li>Attendance</li></a>
        <a href="solutions"><li>Student Solutions</li></a>
    </ul>
</nav>


<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Curriculum</div>
            </div>
            <h1>Published pages</h1>
            <p>Check the box to unpublish the selected page.</p>
            <form action="publishservlet" method="post">
            <ul>
            <c:forEach var="page" items="${pageList}">
                <c:choose>
                <c:when test="${page.isPublished()}">
                <li><input type="checkbox" name="unpublish" value="${page.getTitle()}" />Published: <a href="handlepage?title=${page.getTitle()}">${page.getTitle()}</a></li>
                </c:when>
                </c:choose>
            </c:forEach>
            </ul>
            <br>
            <input type="submit" value="Submit changes">
            <br>
            </form>
            <h1>Unpublished pages</h1>
            <p>Check the box to publish the selected page.</p>
            <form action="publishservlet" method="post">
            <ul>
            <c:forEach var="page" items="${pageList}">
                <c:choose>
                <c:when test="${!page.isPublished()}">
                <li><input type="checkbox" name="publish" value="${page.getTitle()}" />Unpublished: <a href="handlepage?title=${page.getTitle()}">${page.getTitle()}</a></li>
                </c:when>
                </c:choose>
            </c:forEach>
            </ul>
            <br>
            <input type="submit" value="Submit changes">
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
                <li><a href="addassignment.jsp">Add new assignment page</a></li>
                <li><a href="addtextpage.jsp">Add new text page</a></li>
                <li><a href="">Sidebar link 3</a></li>
                <li><a href="">Sidebar link 4</a></li>
            </ul>

        </div>
    </div>

</div>
</body>
</html>
