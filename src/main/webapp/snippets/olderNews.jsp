<%--
  Created by IntelliJ IDEA.
  User: tamy
  Date: 2019.04.08.
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${Older}" var="news">
    <div class="container">
        <div class="containerhead">
            <div class="title"><a href="">${news.getTitle()}</a></div>
            <p class="display_date">${news.getDate()}</p>
            <p class="display_news">${news.getContent()}</p>
        </div>
        <div class="containerfoot"></div>
    </div>
</c:forEach>
