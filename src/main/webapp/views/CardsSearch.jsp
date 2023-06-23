<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Страница поиска карточек</title>
        <style> <%@include file="/resources/css/search.css" %> </style>
        <style> <%@include file="/resources/css/all.css" %> </style>
    </head>
    <body>
    <a href="/menuUser">Главная страница</a><br>
        <h2>Поиск</h2>

            <form class="search" method="post">
                <label>Вывод по категории:
                    <select name="selectcategory" required>
                        <option></option>
                        <option value="id">ID</option>
                        <option value="status">Сатус</option>
                        <option value="revision">Ревизия</option>
                        <option value="title">Краткое описание</option>
                        <option value="titleMore">Полное описание</option>
                        <option value="process">Процесс</option>
                        <option value="type_of_activity">Вид деятельности</option>
                        <option value="record_type">Тип записи</option>
                        <option value="date_of_creation">Дата создания</option>
                        <option value="direction_of_activity">Направление деятельности</option>
                        <option value="equipment">Оборудование</option>
                        <option value="responsible">Ответственный</option>
                    </select><br>
                </label>

                <label>Параметр
                    <input type="search" name="parameter" required><br>
                </label>

                <button type="submit">Найти</button><br>
            </form>
        <a href="/cardssearchlist?ind=allList">Все записи</a><br>
    </body>
</html>
