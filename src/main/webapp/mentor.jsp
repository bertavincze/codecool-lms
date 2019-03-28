<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoatCool</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/text.css">
</head>

<body>

<div class="header">
    <div class="svg-wrapper">
  <svg height="60" width="320" xmlns="http://www.w3.org/2000/svg">
    <rect class="shape" height="60" width="320" />
    <div class="headertext">GoatCool LMS</div>
  </svg>
</div>
</div>

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="userlist"><li>UserList</li></a>
        <a href="curriculum"><li>Curriculum</li></a>
        <a href="attendance"><li>Attendance</li></a>
        <a href="solutions"><li>Student Solutions</li></a>
    </ul>
</nav>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">Welcome</a></div>
            </div>
            <h2>You are now logged in as ${user.getClass().simpleName} ${user.getName()}.</h2>
            <p>
                Here is a random goat for you:

            </p>
                <iframe src='//randomgoat.com/embed.php' width='480' height='360' frameBorder='0' id='random-goat-embed' allowFullScreen></iframe>
            <div class="containerfoot"></div>
        </div>
         <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">News</a></div>
            </div>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum et massa in dui finibus malesuada sed
                    at mi. Ut ultricies mi sed ligula dapibus, pulvinar congue leo mattis. Morbi ornare tempor porttitor.
                    Praesent dignissim rutrum dui, quis venenatis lectus pellentesque id. Aliquam viverra accumsan enim id
                    porta. Morbi fermentum scelerisque eleifend. Aenean placerat accumsan purus, eu scelerisque nisi congue at.
                    Pellentesque ac tempor felis.
                </p>
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
