<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
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
    <link href='https://fonts.googleapis.com/css?family=Roboto|Megrim' rel='stylesheet' type='text/css'>
</head>

<body class="two_div" onload="checkCookie()">

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li class="marked">Curriculum</li></a>
        <a href="attendance"><li>Attendance</li></a>
        <a href="solutions"><li>Student Solutions</li></a>
    </ul>
    <jsp:include page="snippets/themeChanger.jsp"/>
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
                <table>
                    <tr>
                        <th>Page title</th>
                    </tr>
                    <c:forEach var="page" items="${pageList}">
                        <c:choose>
                            <c:when test="${page.isPublished()}">
                                <tr>
                                    <td>
                                        <a href="updatePage?title=${page.getTitle()}&edit=false">${page.getTitle()}</a>
                                    </td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </table>
            <br>
                <table>
                    <tr>
                        <th>Page title</th>
                    </tr>
                    <c:forEach var="page" items="${pageList}">
                        <c:choose>
                            <c:when test="${!page.isPublished()}">
                                <tr>
                                    <td>
                                        <a href="updatePage?title=${page.getTitle()}&edit=false">${page.getTitle()}</a>
                                    </td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </table>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">
            <ul class="links">
                <div class="linktitle">Links</div>
                <li><a href="addassignment.jsp">Add new assignment page</a></li>
                <li><a href="addtextpage.jsp">Add new text page</a></li>
            </ul>
        </div>
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
