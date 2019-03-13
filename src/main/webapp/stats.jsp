<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/profile.css">
</head>

<body>

<div class="header">
    <a href="index.html"><h1>CodeCool LMS</h1></a>
</div>

<nav>
    <ul>
        <a href="userlist.jsp"><li class="marked">UserList</li></a>
        <a href="curriculum.html"><li>Curriculum</li></a>
        <a href="solution.html"><li>Solution</li></a>
        <a href="attendance.html"><li>Attendance</li></a>
        <a href="profile.jsp"><li>Profile</li></a>
    </ul>
</nav>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">Assignment Scores</a></div>
            </div>
                <table>
                    <tr>
                        <td>Name</td>
                        <td>Assignment</td>
                        <td>Score</td>
                    </tr>
                     <c:forEach var="s" items="${user.getSolutionList()}">
                        <tr>
                            <td><c:out value="${s.title}"/> </td>
                            <td><c:out value="${s.answer}"/></td>
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
                                <td>Role: Mentor</td>
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
