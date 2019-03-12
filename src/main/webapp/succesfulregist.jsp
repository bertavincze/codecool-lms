<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/onepager_style.css">
    <title>Greeting</title>
</head>
<body>
    <header>
        <a href="index.html"><h1>CodeCool LMS</h1></a>
    </header>
    <div class="wrapper">
        <div class="log-in-content">
            <div class="thankyou">
                <h1>Thank you for registering as a ${user.getUserRole().getValue()} ${user.getName()}.</h1>
                <p>You can <a href="index.html">log in</a> now!</p>
            </div>
        </div>
    </div>
</body>
</html>
