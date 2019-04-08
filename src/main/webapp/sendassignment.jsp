<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
        <a href="assignments"><li>Assignments</li></a>
        <a href="stats"><li>Stats</li></a>
    </ul>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href=""></a>Assignment</div>
            </div>

                <h2>${page.getTitle()}</h2>
                <p>Question: </p>
                <p>${page.getQuestion()}</p>

                <form action="solution" method="POST">
                    <p>Solution: </p>
                    <p><textarea rows="20" cols="20" name="solution"></textarea></p>
                <br>

                 <input type="hidden" name="title" value="${page.getTitle()}">
                 <input type="hidden" name="question" value="${page.getQuestion()}">
                <p><input class="button" type="submit" value="Submit solution"></p>

            </form>
            <div class="containerfoot"></div>
        </div>
    </div>

    <div class="sidebar">
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
