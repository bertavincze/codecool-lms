<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/onepager_style.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="resources/css/text.css">
    <title>GoatCool</title>
</head>
<body>
    <header>
        <div class="svg-wrapper">
  <svg height="60" width="320" xmlns="http://www.w3.org/2000/svg">
    <rect class="shape" height="60" width="320" />
    <div class="headertext">GoatCool LMS</div>
  </svg>
</div>
    </header>
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
