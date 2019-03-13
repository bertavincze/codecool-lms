<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="hu">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/onepager_style.css">
    <title>GoatCool</title>
</head>
<body>
    <header>
        <a href="index.html"><h1>GoatCool</h1></a>
    </header>
    <div class="wrapper">
        <div class="log-in-content">
            <div class="thankyou">
                <h1>Thank you for registering ${user.getName()}.</h1>
                <p>You can <a href="index.html">log in</a> now!</p>
            </div>
        </div>
    </div>
</body>
</html>
