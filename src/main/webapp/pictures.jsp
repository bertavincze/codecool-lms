<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="javascript/themeChanger.js">
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoatCool</title>
    <link rel="stylesheet" type="text/css" href="resources/css/finalstyle.css"  id="themer">
    <link rel="stylesheet" type="text/css" href="resources/css/temp.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto|Megrim' rel='stylesheet' type='text/css'>
</head>

<body class="two_div" onload="checkCookie()">
<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
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
        <jsp:include page="snippets/themeChanger.jsp"/>
</nav>

<script>
    function toMarked() {
        set.style.color= "#221c5a";
    }
</script>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">Profile pictures</a></div>
            </div>
            <form action="changePic" method="post">
                <div class="row">
                    <div class="third">
                        <input name="pic" type="radio" value="resources/pics/profile.jpg"><img src="resources/pics/profile.jpg"/>
                    </div>
                    <div class="third">
                        <input name="pic" type="radio" value="resources/pics/pic2.jpg"><img src="resources/pics/pic2.jpg"/>
                    </div>
                    <div class="third">
                        <input name="pic" type="radio" value="resources/pics/pic3.jpg"><img src="resources/pics/pic3.jpg"/>
                    </div>
                </div>
                <div class="row">
                    <div class="third">
                        <input name="pic" type="radio" value="resources/pics/pic4.jpg"><img src="resources/pics/pic4.jpg"/>
                    </div>
                    <div class="third">
                        <input name="pic" type="radio" value="resources/pics/cage5.jpg"><img src="resources/pics/cage5.jpg"/>
                    </div>
                    <div class="third">
                        <input name="pic" type="radio" value="resources/pics/pic6.png"><img src="resources/pics/pic6.png"/>
                    </div>
                </div>
                <input type="submit" class="button" value="Save">
            </form>
            <br>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
