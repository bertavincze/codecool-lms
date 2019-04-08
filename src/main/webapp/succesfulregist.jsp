<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!doctype html>
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
            <div class="thankyou">
                <h2>Thank you for registering ${user.getName()}.</h2>
                <p>You can <a href="index.html">log in</a> now!</p>
            </div>
        </div>
    </div>
</body>
</html>
