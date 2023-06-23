<%--
  Created by IntelliJ IDEA.
  User: Администратор
  Date: 16.05.2023
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Главное меню Администратора</title>
<%--        <style> <%@include file="/resources/css/all.css" %> </style>--%>
    </head>
    <body>
    <table>
        <tr>
            <td>
                <%
                    String nameUser = (String) request.getSession().getAttribute("user");
                    out.println("<h2>Привет " + nameUser + "!</h2>");
                    session.setMaxInactiveInterval(60 * 60);
                %>
            </td>
            <td>
                <a href="/index">Выйти</a>
            </td>
        </tr>
    </table>
        <div>
            <h2>Меню администратора</h2>
            <button onclick="location.href='/add'">Добавление пользователя</button>
            <button onclick="location.href='/list'">Список пользователей</button>
        </div>
    </body>
</html>
