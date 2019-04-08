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
        <a href="attendance"><li>Attendance</li></a>
        <a href="solutions"><li>Student Solutions</li></a>
    </ul>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Curriculum</div>
            </div>
            <h2>Published pages</h2>
            <p>Check the box to unpublish the selected page.</p>
            <form action="publishservlet" method="post">
            <table>
            <tr>
            <th>Page title</th>
            <th>Check to unpublish</th>
            </tr>
            <c:forEach var="page" items="${pageList}">
                <c:choose>
                <c:when test="${page.isPublished()}">
                <tr>
                <td>
                <a href="handlepage?title=${page.getTitle()}&edit=false">${page.getTitle()}</a>
                </td>
                <td>
                <label class="switch">
                <input type="checkbox" name="unpublish" value="${page.getTitle()}" />
                <span class="slider"></span>
                </label>
                </td>
                </tr>
                </c:when>
                </c:choose>
            </c:forEach>
            </table>
            <br>
            <input class="button" type="submit" value="Submit changes">
            <br>
            </form>
            <h2>Unpublished pages</h2>
            <p>Check the box to publish the selected page.</p>
            <form action="publishservlet" method="post">
            <table>
            <tr>
            <th>Page title</th>
            <th>Check to publish</th>
            </tr>
            <c:forEach var="page" items="${pageList}">
                <c:choose>
                <c:when test="${!page.isPublished()}">
                <tr>
                <td>
                <a href="handlepage?title=${page.getTitle()}&edit=false">${page.getTitle()}</a>
                </td>
                <td>
                <label class="switch">
                <input type="checkbox" name="publish" value="${page.getTitle()}" />
                <span class="slider"></span>
                </label>
                </td>
                </tr>
                </c:when>
                </c:choose>
            </c:forEach>
            </table>
            <br>
            <input class="button" type="submit" value="Submit changes">
            <br>
            </form>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">
            <ul class="links">
                <div class="linktitle">Favourites</div>
                <li><a href="addassignment.jsp">Add new assignment page</a></li>
                <li><a href="addtextpage.jsp">Add new text page</a></li>
            </ul>
        </div>
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
