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
        <a href="curriculum"><li>Curriculum</li></a>
        <a href="assignments"><li>Assignments</li></a>
        <a href="stats"><li>Stats</li></a>
    </ul>
</nav>

<jsp:include page="snippets/header.jsp" />

<div class="wrapper">
    <div class="content">
        <jsp:include page="snippets/latestNews.jsp" />
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
        <jsp:include page="snippets/olderNews.jsp" />
    </div>

    <div class="sidebar">
        <jsp:include page="snippets/sidebar.jsp" />
    </div>

</div>
</body>
</html>
