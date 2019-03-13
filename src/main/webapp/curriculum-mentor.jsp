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
        <a href="curriculum"><li>Curriculum</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="assignment.html"><li>Assignments</li></a>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="stats"><li>Stats</li></a>
    </ul>
</nav>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Curriculum</div>
            </div>
            <p>Modify page status below:</p>
            <form action="publishservlet" method="">
            <ul>
            <c:forEach var="page" items="${pageList}">
                <c:choose>
                <c:when test="${page.isPublished()}">
                <li><input type="checkbox" name="unpublish" value="${page.getTitle()}" />Unpublish: <a href="">${page.getTitle()}</a></li>

                </c:when>
                <c:otherwise>
                <li><input type="checkbox" name="publish" value="${page.getTitle()}" />Publish: <a href="">${page.getTitle()}</a></li>

                </c:otherwise>
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
            </div>

            <ul class="links">
                <div class="linktitle">Favourites</div>
                <li><a href="/">Sidebar link 1</a></li>
                <li><a href="/">Sidebar link 2</a></li>
                <li><a href="/">Sidebar link 3</a></li>
                <li><a href="/">Sidebar link 4</a></li>
            </ul>

        </div>
    </div>

</div>
</body>
</html>
