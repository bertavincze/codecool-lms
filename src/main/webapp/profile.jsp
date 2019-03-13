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
    <link rel="stylesheet" type="text/css" href="resources/css/profile.css">
    <title>GoatCool</title>
</head>
<body>
<script type="text/javascript">
    function editName() {
        var popup = document.getElementById("editName");
        popup.classList.toggle("show");
    }
    function editMail() {
         var popup = document.getElementById("editMail");
         popup.classList.toggle("show");
        }
</script>

<div class="header">
    <a href="index.html"><h1>GoatCool</h1></a>
</div>

<nav>
    <ul>
        <a href="profile.jsp"><li class="marked">Profile</li></a>
        <a href="curriculum"><li>Curriculum</li></a>
        <a href="assignment.html"><li>Assignments</li></a>
        <a href="stats.html"><li>Stats</li></a>
    </ul>
</nav>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title">Profile</div>
            </div>
            <div class="one-third">
            </div>
                <div class="two-third">
                     <table>
                      <tr>
                        <td>Name: </td>
                        <td><p>${user.getName()}</p>
                            <form id="editName" action = "editedName" method = "post">
                                <input class="popupbox" type = "text" name = "name">
                                <input class="popupbutton" type = "submit" value = "Save" />
                            </form>
                        </td>
                        <td class="icon" onclick="editName()">
                        </td>
                      </tr>
                      <tr>
                        <td>E-mail: </td>
                        <td><p>${user.getEmail()}</p>
                            <form id="editMail" action = "editedMail" method = "post">
                                <input  class="popupbox" type = "text" name = "email">
                                <input  class="popupbutton" type = "submit" value = "Save" />
                            </form>
                        </td>
                        <td class="icon" onclick="editMail()">
                        </td>
                      </tr>
                      <tr>
                        <td>Role: </td>
                        <td>
                        <c:choose>
                            <c:when test="${user.getClass().simpleName == 'Student'}">
                                <p>Student</p>
                            </c:when>
                            <c:otherwise>
                                <p>Mentor</p>
                            </c:otherwise>
                            </c:choose>
                        </td>
                      </tr>
                    </table>

                </div>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">
            <div class="desc">
                <p>Welcome ${user.getName()}! You can see and edit your basic data and track your progress here.</p> <br>





            </div>
            <div class="containerhead">
                <div class="title"></div>
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
