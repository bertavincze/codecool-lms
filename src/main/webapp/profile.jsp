<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/profile.css">
    <title>GoatCool</title>
</head>
<body>

<div class="header">
    <a href="index.html"><h1>GoatCool</h1></a>
</div>

<nav>
    <ul>
        <a href="profile.jsp"><li class="marked">Profile</li></a>
        <a href="curriculum.html"><li>Curriculum</li></a>
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
                        <td>${user.getName()}</td>
                      </tr>
                      <tr>
                        <td>E-mail: </td>
                        <td>${user.getEmail()}</td>
                      </tr>
                      <tr>
                        <td>Role: </td>
                        <td>${user.getUserRole().getValue()}</td>
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
                <p><a href="/">Edit profile</a></p><br>

                <!-- <div class="popup" onclick="myFunction()">Click me to toggle the popup!
                    <form class="popuptext" id="myPopup" action = "/codecool-lms/editName" method = "post">
                        <p>Name: <input type = "text" name = "name"></p>
                        <input type = "submit" value = "Register!" />
                    </form>
                </div>

                <script>

                function myFunction() {
                  var popup = document.getElementById("myPopup");
                  popup.classList.toggle("show");
                }
                </script> -->


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
