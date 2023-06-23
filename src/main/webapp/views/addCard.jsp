<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Создание записи</title>
        <style> <%@include file="/resources/css/all.css" %> </style>
        <style> <%@include file="/resources/css/addCard.css" %> </style>
    </head>

    <body>

    <div class="menu">
        <table>
            <tr>
                <a href="/menuUser">Главная страница</a>
            </tr>
        </table>
    </div>

    <%String nameUser = (String) request.getSession().getAttribute("user");%>

        <div class="wrapper">
            <h2>Форма добавления карточки</h2>
<%--Может понадобиться при выводе сообщений о превышенном количестве вложений
            <div class="message">
                <%
                    if(request.getAttribute("createObj") !=null){
                        String Message = (String) request.getAttribute("createObj");
                        out.println(Message);
                    }
                %>
            </div>--%>

            <form enctype="multipart/form-data" class = "cardinput" method="post">

                <div class="top-content">
                    <label class="title">Краткое описание</label>
                        <input type="text" name="title" class="title" required>

                    <br><br>

                    <label>Полное описание</label><br>
                    <textarea name="titleMore" class="title-more" required></textarea>
                    <br><br>

                    <label>ID
                         <input type="text" disabled value="${ID}">
                    </label>

                    <label>Статус
                        <select name="status">
                            <option></option>
                            <option value="актуально">актуально</option>
                            <option value="не актуально">не актуально</option>
                        </select>
                    </label>

                    <label>Ревизия
                        <input type="text" name="revision" pattern="[0-9]{2}" placeholder="формат 00" value="00">
                    </label>

                    <label>Тип записи
                        <select name="record_type">
                            <option></option>
                            <option value="проблематика">проблематика</option>
                            <option value="решение">решение</option>
                            <option value="информация">информация</option>
                        </select>
                    </label><br><br>

                    <label> Вложение
                        <input type="file" name="file" multiple>
                    </label><br><br>

                    <div class="label"> Направление/Процесс</div><br>
                    <div class="content">
                        <label> Процесс
                            <select name="process">
                                <option></option>
                                <option value="инжиниринг">инжиниринг</option>
                                <option value="комплектация">комплектация</option>
                                <option value="производство">производство</option>
                                <option value="строительно-монтажные работы">строительно-монтажные работы</option>
                                <option value="пуско-наладочные работы">пуско-наладочные работы</option>
                                <option value="управление проектом">управление проектом</option>
                                <option value="эксплуатационное обслуживание">эксплуатационное обслуживание</option>
                            </select>
                        </label>

                        <label> Вид деятельности
                            <select name="type_of_activity">
                                <option></option>
                                <option value="инжиниринг">инжиниринг</option>
                                <option value="комплектация">комплектация</option>
                                <option value="строительно-монтажные работы">строительно-монтажные работы</option>
                                <option value="пуско-наладочные работы">пуско-наладочные работы</option>
                            </select>
                        </label><br><br>

                        <label> Направление деятельности
                            <select name="direction_of_activity">
                                <option></option>
                                <option value="АСУ">Автоматизированные системы управления</option>
                                <option value="ЭТО">Электрооборудование</option>
                                <option value="КИП">Контрольно-измерительные приборы</option>
                                <option value="Сети связи">Сети связи</option>
                                <option value="Разработка ППО">Разработка ППО</option>
                                <option value="Блочно-модульное оборудование">Блочно-модульное оборудование</option>
                            </select>
                        </label>

                        <label> Оборудование
                            <select name="equipment">
                                <option></option>
                                <option value="датчик">датчик</option>
                                <option value="кабельный ввод">кабельный ввод</option>
                            </select>
                        </label><br><br>
                    </div>

                    <div class="label">Связи</div><br>
                    <div class="content">
                        <label> Связанные записи
                            <input type="text" name="direction_of_activity">
                        </label>

                        <label> Ответственный
                            <input type="text" name="responsible" value="<%=nameUser%>">
                        </label><br><br>
                    </div>
                </div> <%--close div content--%>

                <div class="bottom-content">
                    <div class="date">
                        <label>Дата создания
                            <input id="date_of_creation" name="date_of_creation"/>
                        </label>
                    </div>
                    <br><br>
                    <button type="submit">Внести запись в БД</button>
                </div>
            </form>
        </div> <%--close wrapper--%>

    </body>
    <script type="text/javascript">
        var date = new Date();
        document.getElementById("date_of_creation").value = (date.getDate()<10?'0':'') + date.getDate() + "." + (date.getMonth()<10?'0':'') + (date.getMonth() + 1) + "." + date.getFullYear();
    </script>
</html>
