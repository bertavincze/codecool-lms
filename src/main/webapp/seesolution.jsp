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
         <jsp:include page="snippets/themeChanger.jsp"/>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href=""></a>Solution</div>
            </div>
                <h2>${assignmentPage.getTitle()}</h2>
                <p>Question: </p>
                <p>${assignmentPage.getQuestion()}</p>

                <p>Solution: </p>
                <p>${solution.getAnswer()}</p>


                <c:choose>
                  <c:when test="${solution.getGrade() == 0}">
                    <p>The solution has not been graded yet!</p>
                  </c:when>
                  <c:otherwise>
                    <p>Current grade: ${solution.getGrade()}</p>
                  </c:otherwise>
                </c:choose>

                <c:choose>
                      <c:when test="${user.getClass().simpleName == 'Mentor'}">
                           <form action="grader" method="post">
                               <input type="hidden" name="title" value="${assignmentPage.getTitle()}">
                               <p>Grade: </p>
                               <select name="grade">
                                   <c:forEach var = "i" begin = "1" end = "${assignmentPage.getMaxScore()}">
                                        <option value="${i}">${i}</option>
                                   </c:forEach>
                               </select>
                               <br><br>
                               <p><input class="button" type="submit"></p>
                           </form>
                      </c:when>
                </c:choose>
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
