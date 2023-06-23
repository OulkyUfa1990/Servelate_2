<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 20.04.2023
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
    <style> <%@include file="/resources/css/all.css" %> </style>
</head>
<body>
    <div>
        <div>
            <h1>Список пользователей</h1>
        </div>

    <%
        List<String> names = (List<String>) request.getAttribute("userNames");

        if (names != null && !names.isEmpty()) {
            out.println("<ui>");
            for (String s : names) {
                out.println("<li>" + s + "</li>");
            }
            out.println("</ui>");
        } else out.println("Пользователей пока нет!");
    %>
        <div>
            <button onclick="location.href='menuAdmin'">Вернуться на главную страницу</button>
        </div>
    </div>
</body>
</html>
