<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Главное меню пользователя</title>
        <style> <%@include file="/resources/css/all.css" %> </style>
        <style> <%@include file="/resources/css/menuUser.css" %> </style>
    </head>
    <body>
        <table class="user">
            <tr>
                <td>
            <%
                String nameUser = (String) request.getSession().getAttribute("user");
                out.println("<b>Привет " + nameUser + "!</b>");
                session.setMaxInactiveInterval(60 * 60);
            %>
                </td>
                <td>
                    <a href="/index"><img src="/resources/image/exit3.png" width="30" height="30" alt="Выход"></a>
                </td>
            </tr>
        </table><br><br>

        <div class="menu">
            <table>
                <tr>
                    <td>
                        <div class="addcard">
                            <a href="/AddCard">ДОБАВИТЬ КАРТОЧКУ</a>
                        </div>
                    </td>
                    <td>
                        <div class="search">
                            <a href="/cardssearch">ПОИСК КАРТОЧКИ</a><br>
                            <div class="list-menu">
                                <ul>
                                    <li>просмотр</li>
                                    <li>редактирование</li>
                                    <li>выпуск ревизии</li>
                                </ul>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>

        </div>
        <%
            int k = (int) request.getAttribute("numbercards");
        %>
        <div class="numbercards">
            Статей<br>в базе
            <%
                out.println("<h2>" + k + "</h2>");
            %>
        </div>
    </body>
</html>
