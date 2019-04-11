<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="title"><a href="">New Assignment</a></div>
            </div>
            <h2>Add new assignment</h2>
            <form action="updatePage" method="post">
                <input type="hidden" value="${id}" name="id">

                <p>Title:</p>
                <p><input class="box" type="text" name="title" value="${title}" autofocus><c:out value="${title}"/></p>

                <p>Question: </p>
                <p><textarea rows="20" cols="20" name="question"><c:out value="${content}"/></textarea></p>

                <br><br>

                <p><input class="button" type="submit"></p>

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
