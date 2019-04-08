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
<script type="text/javascript">
    function editName() {
        var popup = document.getElementById("editName");
        popup.classList.toggle("show");
    }
    function editMail() {
         var popup = document.getElementById("editMail");
         popup.classList.toggle("show");
        }
    function editPassword() {
        var popup = document.getElementById("editPassword");
        popup.classList.toggle("show");
    }
</script>

<nav>
    <ul>
        <a href="profile.jsp"><li class="marked">Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
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
                <div class="title">Profile</div>
            </div>
            <div class="one-third">
            </div>
                <div class="two-third">
                     <table class="profile">
                      <tr>
                        <td>Name: </td>
                        <td><p>${user.getName()}</p>
                            <form id="editName" action = "editedName" method = "post" class="hide_form">
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
                            <form id="editMail" action = "editedMail" method = "post" class="hide_form">
                                <input  class="popupbox" type = "text" name = "email">
                                <input  class="popupbutton" type = "submit" value = "Save" />
                            </form>
                        </td>
                        <td class="icon" onclick="editMail()">
                        </td>
                      </tr>
                     <tr>
                         <td>Password: </td>
                         <td><p>${user.getPasswordDisplay()}</p>
                             <form id="editPassword" action = "editedPassword" method = "post" class="hide_form">
                                 <input  class="popupbox" type = "text" name = "password">
                                 <input  class="popupbutton" type = "submit" value = "Save" />
                             </form>
                         </td>
                         <td class="icon" onclick="editPassword()">
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
            <div class="containerfoot" id="profile_foot"></div>
        </div>
    </div>

    <div class="sidebar">
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
