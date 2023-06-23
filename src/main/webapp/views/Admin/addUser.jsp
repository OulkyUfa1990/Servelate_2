<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Add new user</title>
</head>

<body>

    <div>
    <%

        if (request.getAttribute("Status") != null) {
            out.println(request.getAttribute("userName"));
        }
    %>
        <div>
            <div>
                <h2>Add user</h2>
            </div>

            <form method="post">
                <label>Имя:
                    <input type="text" name="name" ><br>
                </label>

                <label>Пароль:
                    <input type="password" name="pass" ><br>
                </label>
                <button type="submit" >Отправить</button>
            </form>
        </div>

    </div>

    <div>
        <button onclick="location.href='menuAser'">Вернуться на главную страницу</button>
    </div>

</body>

</html>
