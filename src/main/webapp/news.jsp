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
    <link rel="stylesheet" type="text/css" href="resources/css/finalstyle.css" id="themer">
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


<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <c:choose>
            <c:when test="${user.getClass().simpleName == 'Mentor'}">
                <div class="container">
                    <div class="containerhead">
                        <div class="title"><a href="">Add news here</a></div>
                    </div>
                    <form action="news" method="post">
                        <input class="box" type="text" placeholder="Title" maxlength="100" name="title"><br>
                        <textarea rows = "2" name = "content" maxlength="300" placeholder="News content required (300char max)..."></textarea><br>
                        <input class="button" type="submit" value="Send"><br>
                    </form>
                    <div class="containerfoot"></div>
                </div>
            </c:when>
            <c:otherwise/>
        </c:choose>
        <jsp:include page="snippets/latestNews.jsp" />
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">News</a></div>
            </div>
            <!-- start feedwind code --> <script type="text/javascript" src="https://feed.mikle.com/js/fw-loader.js" data-fw-param="106479/"></script> <!-- end feedwind code -->
            <div class="containerfoot"></div>
        </div>
        <jsp:include page="snippets/olderNews.jsp" />
    </div>

    <div class="sidebar">
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
