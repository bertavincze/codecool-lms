<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoatCool</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Aladin|Merienda+One|Walter+Turncoat" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="resources/css/text.css">
</head>

<body>

<div class="header">
    <a href="index.html"><h1>GoatCool</h1></a>
</div>

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li class="marked">Curriculum</li></a>
        <a href="assignments"><li>Assignments</li></a>
        <a href="stats"><li>Stats</li></a>
    </ul>
</nav>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href=""></a>Solution</div>
            </div>
                <h2>${assignmentPage.getTitle()}</h2>
                <p>Question: </p>
                <p>${assignmentPage.getQuestion()}</p>

                <p>Your answer: </p>
                <p>${solution.getAnswer()}</p>


                <c:choose>
                  <c:when test="${solution.getGrade() == null}">
                    <p>The solution has not been graded yet!</p>
                  </c:when>
                  <c:otherwise>
                        <p>Current grade: "${solution.getGrade()}"</p>
                  </c:otherwise>
                </c:choose>

                <c:choose>
                      <c:when test="${user.getClass().simpleName == 'Mentor'}">
                           <form action="grader" method="post">

                               <p>Grade: </p>
                               <select name="grade">
                                   <c:forEach var = "i" begin = "1" end = "${solution.getMaxScore()}">
                                        <option value=<c:out value='"${i}"'/>><c:out value="${i}"/></option>
                                   </c:forEach>
                               </select>
                               <br><br>
                               <p><input type="submit"></p>
                           </form>
                      </c:when>
                </c:choose>
                <br>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <div class="sbcontainer">
            <div class="containerhead">
                <div class="title">Current user</div>
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
                <li><a href="">Sidebar link 1</a></li>
                <li><a href="">Sidebar link 2</a></li>
                <li><a href="">Sidebar link 3</a></li>
                <li><a href="">Sidebar link 4</a></li>
            </ul>

        </div>
    </div>

</div>
</body>
</html>
