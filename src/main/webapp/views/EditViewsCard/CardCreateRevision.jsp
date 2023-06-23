<%@ page import="app.card.entities.ListRevision" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
    <head>
        <title>Создание ревизии карточки</title>
        <style> <%@include file="/resources/css/all.css" %> </style>
        <style> <%@include file="/resources/css/addCard.css" %> </style>
    </head>
    <body>

        <div class="menu">
            <a href="/menuUser">Вернуться на главную страницу</a>
        </div>

        <%String nameUser = (String) request.getSession().getAttribute("user");%>

        <div class="wrapper">
            <h2>Форма создания ревизии карточки</h2>
            <div class="message">
                <%
                    if(request.getAttribute("createrevision") !=null){
                        String Message = (String) request.getAttribute("createrevision");
                        out.println(Message);
                    }
                %>
            </div>

                <form enctype="multipart/form-data" class = "cardinput" method="post">
                    <div class="content">
                            <label>Краткое описание</label>
                            <input type="text" name="title" class="title" value="${cardcreatrevision[4]}">
                            <br>

                            <label>Полное описание</label><br>
                            <textarea name="titleMore" class="title-more" required>${cardcreatrevision[5]}</textarea>
                            <br><br>

                            <label>ID
                                <input type="text" name="idС" required value="${idCCV}">
                            </label>

                            <label>Статус
                                <select name="status">
                                    <option>${cardcreatrevision[2]}</option>
                                    <option value="актуально">актуально</option>
                                    <option value="не актуально">не актуально</option>
                                </select>
                            </label>

                            <label>Ревизия
                                <input type="text" name="revision" pattern="[0-9]{2}" placeholder="формат 00" value="${creatRev}">
                            </label>

                            <label>Тип записи
                                <select name="record_type">
                                    <option>${cardcreatrevision[8]}</option>
                                    <option value="проблематика">проблематика</option>
                                    <option value="решение">решение</option>
                                    <option value="информация">информация</option>
                                </select>
                            </label><br><br>

<%--                            <label>Вложения:</label><br>
                            <%
                                ArrayList<String> cardcreatrevision = new ArrayList<>();
                                cardcreatrevision = (ArrayList<String>) request.getSession().getAttribute("cardcreatrevision");
                                if (cardcreatrevision.get(15) != null) {%>

                                    <a href="/contentviews?id=${cardcreatrevision[1]}&cont=15&cont_type=${cardcreatrevision[16]}" target="_blank">${cardcreatrevision[15]}</a><br>
                                    <a href="/contentviews?id=${cardcreatrevision[1]}&cont=18&cont_type=${cardcreatrevision[19]}" target="_blank">${cardcreatrevision[18]}</a><br>
                                    <a href="/contentviews?id=${cardcreatrevision[1]}&cont=21&cont_type=${cardcreatrevision[22]}" target="_blank">${cardcreatrevision[21]}</a><br>
                                    <a href="/contentviews?id=${cardcreatrevision[1]}&cont=24&cont_type=${cardcreatrevision[25]}" target="_blank">${cardcreatrevision[24]}</a><br>
                                    <a href="/contentviews?id=${cardcreatrevision[1]}&cont=27&cont_type=${cardcreatrevision[28]}" target="_blank">${cardcreatrevision[27]}</a><br>
                            <%
                                } else out.println("<span class=\"stick\"> Вложений нет </span>");
                            %>
                            <br><br>--%>

                            <label> Добавить вложение
                                <input type="file" name="file" multiple>
                            </label><br><br>

                            <div class="label">Направление/Процесс</div><br>
                                <label>Процесс
                                    <select name="process">
                                        <option>${cardcreatrevision[6]}</option>
                                        <option value="инжиниринг">инжиниринг</option>
                                        <option value="комплектация">комплектация</option>
                                        <option value="производство">производство</option>
                                        <option value="строительно-монтажные работы">строительно-монтажные работы</option>
                                        <option value="пуско-наладочные работы">пуско-наладочные работы</option>
                                        <option value="управление проектом">управление проектом</option>
                                        <option value="эксплуатационное обслуживание">эксплуатационное обслуживание</option>
                                    </select>
                                </label>

                                <label>Вид деятельности
                                    <select name="type_of_activity">
                                        <option>${cardcreatrevision[7]}</option>
                                        <option value="инжиниринг">инжиниринг</option>
                                        <option value="комплектация">комплектация</option>
                                        <option value="строительно-монтажные работы">строительно-монтажные работы</option>
                                        <option value="пуско-наладочные работы">пуско-наладочные работы</option>
                                    </select>
                                </label>

                                <label>Направление деятельности
                                    <select name="direction_of_activity">
                                        <option>${cardcreatrevision[11]}</option>
                                        <option value="АСУ">Автоматизированные системы управления</option>
                                        <option value="ЭТО">Электрооборудование</option>
                                        <option value="КИП">Контрольно-измерительные приборы</option>
                                        <option value="Сети связи">Сети связи</option>
                                        <option value="Разработка ППО">Разработка ППО</option>
                                        <option value="Блочно-модульное оборудование">Блочно-модульное оборудование</option>
                                    </select>
                                </label>

                                <label>Оборудование
                                    <select name="equipment">
                                        <option>${cardcreatrevision[12]}</option>
                                        <option value="датчик">датчик</option>
                                        <option value="кабельный ввод">кабельный ввод</option>
                                    </select>
                                </label><br><br>
                            </div>

                            <div class="label">Связи</div>

                            <%
                                ArrayList<ListRevision> list;
                                list = (ArrayList<ListRevision>)request.getSession().getAttribute("LinkListRevision");
                                if (list != null){
                            %>
                            <table>
                                <tr><td>Ревизии:</td>
                                    <%for (ListRevision i : list){%>
                                    <td><a href="/cardout?id=<%=i.getId()%>">rev.<%=i.getRev()%></a></td>
                                    <%
                                    }
                                } else out.println("Ничего не найдено!");%>
                                </tr>
                            </table><br>

                                <label>Связанные записи
                                    <input type="text" name="related_records">
                                </label>

                                <label>Ответственный
                                    <input type="text" name="responsible" value="<%=nameUser%>">
                                </label><br><br>

                    <div class="bottom-content">
                        <div class="date">
                            <label>Дата создания<br>
                                <input id="date_of_creation" name="date_of_creation"/>
                            </label>
                        </div>
                        <br><br>
                        <button type="submit">Выпустить ревизию карточки</button>
                    </div>
                </form>
        </div>

        <script type="text/javascript">
            var date = new Date();
            document.getElementById("date_of_creation").value = (date.getDate()<10?'0':'') + date.getDate() + "." + (date.getMonth()<10?'0':'') + (date.getMonth() + 1) + "." + date.getFullYear();
        </script>
    </body>
</html>
