<%--
  Created by IntelliJ IDEA.
  User: tamy
  Date: 2019.04.08.
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="containerhead">
        <div class="title"><a href="">${Current.getTitle()}</a></div>
        <p class="display_date">${Current.getDate()}</p>
        <p class="display_news">${Current.getContent()}</p>
    </div>
    <div class="containerfoot"></div>
</div>
