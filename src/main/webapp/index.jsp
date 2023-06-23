<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <style> <%@include file="/resources/css/all.css" %> </style>
    <style> <%@include file="/resources/css/index.css" %> </style>
</head>
<body>
    <div class="login">
        <p class="error">
        <%
            if (request.getAttribute("errorMessage") != null) {
                String errorMessage = (String) request.getAttribute("errorMessage");
                out.println(errorMessage);
            }
        %>
        </p>
        <h2>Авторизация</h2>
        <form class="welcome" method="post">
            <label>Имя пользователя:
                <input type="text" name="login" required><br>
            </label>
            <label>Пароль:
                <input type="password" name="pass" required><br>
            </label>
            <button type="submit">Войти</button>
        </form>

    </div>
</body>
</html>
