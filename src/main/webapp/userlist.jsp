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
</head>

<body>

<div class="header">
    <a href="index.html"><h1>CodeCool LMS</h1></a>
</div>

<nav>
    <ul>
        <a href="/codecool-lms/userlist"><li>UserList</li></a>
        <a href="profile.html"><li>Profile</li></a>
        <a href="curriculum.html"><li>Curriculum</li></a>
        <a href="solution.html"><li>Solution</li></a>
        <a href="attendance.html"><li>Attendance</li></a>
    </ul>
</nav>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">Registered Users</a></div>
            </div>
                <table>
                    <tr>
                        <td><c:out value="name"/> </td>
                        <td><c:out value="email"/></td>
                        <td><c:out value="role"/></td>
                    </tr>
                    <c:forEach var="u" items="${users}">
                        <tr>
                            <td><c:out value="${u.name}"/> </td>
                            <td><c:out value="${u.email}"/></td>
                            <td><c:out value="${u.userRole}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">
            <div class="containerhead">
                <div class="title">Sidebar title</div>
            </div>

            <div class="desc">
                <p>
                    Text Text Text Text Text Text Text Text Text Text Text Text
                </p>

            </div>

            <ul class="links">
                <div class="linktitle">Navigation</div>
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
