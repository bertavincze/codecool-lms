<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GoatCool</title>
    <link rel="stylesheet" type="text/css" href="resources/css/finalstyle.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto|Megrim' rel='stylesheet' type='text/css'>
</head>

<body class="one_div">

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="log-in-content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">Registration</a></div>
            </div>

            <p>Welcome to GoatCool! This is a restricted access page, only for registered users.</p>


            <form action="register" method="post">


                <p>Name: <input class="box" type="text" name="name" autofocus></p>
                <p>E-mail: <input class="box" type="text" name="email"/></p>
                <p>Password: <input type="password" name="password"/></p>
                <p class="error-message"><c:out value="${error}"/></p>
                <div id="radiobutton">
                    <p><input type="radio" name="status" value="mentor"/> Mentor
                        <input type="radio" name="status" value="student"/> Student</p>
                </div>
                <input class="button" type="submit" value="Register!"/>

            </form>

            <p class="redirect">Already have an account? <a href="login">Log in!</a></p>
            <div class="containerfoot"></div>

        </div>
    </div>

</div>
</body>
</html>

