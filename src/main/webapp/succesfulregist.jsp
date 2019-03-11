<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting</title>
</head>
<body>
<h1>Thank you for registering as a ${user.getUserRole().getValue()} ${user.getName()}.</h1>
<a href="index.html">Go back to the <em>index</em> page.</a>
<br>
</body>
</html>
