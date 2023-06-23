<%@ page import="app.card.entities.ListRevision" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
    <head>
        <title>Просмотр картчоки</title>
        <style> <%@include file="/resources/css/all.css" %> </style>
        <style> <%@include file="/resources/css/OutCard.css" %> </style>
    </head>
    <body>
    <div class="menu">
        <a href="/menuUser">Вернуться на главную страницу</a>
        <a href="/cardssearchlist">Назад в результат поиска</a>
    </div>

    <div class="wrapper">
        <h2>Просмотр карточки</h2>

        <div class="message">
            <%
                if(request.getAttribute("createObj") !=null){
                    String Message = (String) request.getAttribute("createObj");
                    out.println(Message + "<br><br>");
                }
            %>
        </div>

        <form>
                <span class="stick">Краткое описание</span><br>
                <label class="title" name="title" >${cardout[4]}</label><br>

                <label class="stick">Полное описание</label><br>
                <label class="views" name="titleMore" required class="title-more">${cardout[5]}</label>
                <br>

                <div class="block">
                <label class="stick">ID</label><br>
                <label class="views" name="idС" required>${cardout[1]}</label>
                </div>

                <div class="block">
                <label class="stick">Статус</label><br>
                <label class="views">${cardout[2]}</label>
                </div>

                <div class="block">
                <label class="stick">Ревизия</label><br>
                <label class="views">${cardout[3]}</label>
                </div>

                <div class="block">
                <label class="stick">Тип записи</label><br>
                <label class="views">${cardout[8]}</label>
                </div><br><br>


                <label class="stick">Вложения:</label><br>
            <%
                ArrayList<String> cardout = new ArrayList<>();
                cardout = (ArrayList<String>) request.getSession().getAttribute("cardout");
                if (cardout.get(15) != null) {%>

                    <a class="link" href="/contentviews?id=${cardout[1]}&cont=15&cont_type=${cardout[16]}" target="_blank">${cardout[15]}</a>
                    <a class="link" href="/contentviews?id=${cardout[1]}&cont=18&cont_type=${cardout[19]}" target="_blank">${cardout[18]}</a>
                    <a class="link" href="/contentviews?id=${cardout[1]}&cont=21&cont_type=${cardout[22]}" target="_blank">${cardout[21]}</a>
                    <a class="link" href="/contentviews?id=${cardout[1]}&cont=24&cont_type=${cardout[25]}" target="_blank">${cardout[24]}</a>
                    <a class="link" href="/contentviews?id=${cardout[1]}&cont=27&cont_type=${cardout[28]}" target="_blank">${cardout[27]}</a>
            <%
                } else out.println("<span class=\"stick\"> Вложений нет </span>");
            %>
                <br><br>

                <span class="l-text">Направление/Процесс</span>
                <div class="label"></div><br>

                    <table>
                        <tr>
                            <td>
                    <label class="stick">Процесс</label><br>
                    <label class="views">${cardout[6]}</label>
                            </td>
                            <td>
                    <label class="stick">Вид деятельности</label><br>
                    <label class="views">${cardout[7]}</label>
                            </td>
                            <td>
                    <label class="stick">Направление деятельности</label><br>
                    <label class="views">${cardout[11]}</label>
                            </td>
                            <td>
                    <label class="stick">Оборудование</label><br>
                    <label class="views">${cardout[12]}</label>
                            <td>
                        </tr>
                    </table>
                    <br>

                <span class="l-text">Связи</span>
                <div class="label"></div><br>
                    <%
                        ArrayList<ListRevision> list;
                        list = (ArrayList<ListRevision>)request.getSession().getAttribute("LinkListRevision");
                        if (list != null){
                    %>
                    <table>
                        <tr><td><label class="stick">Ревизии:</label></td>
                            <%for (ListRevision i : list){%>
                            <td><a class="link" href="/cardout?id=<%=i.getId()%>">rev.<%=i.getRev()%></a></td>
                            <%
                                    }
                                } else out.println("Ничего не найдено!");%>
                        </tr>
                    </table><br>

                    <table>
                        <tr>
                            <td>
                    <label class="stick">Связанные записи</label><br>
                    <label class="views">Связанные записи</label>
                            </td>
                            <td>
                    <label class="stick">Ответственный</label><br>
                    <label class="views">${cardout[13]}</label>
                            </td>
                            <td>
                    <label class="stick">Ссылка на ревизию</label><br>
                    <label class="views">Ссылка на ревизию</label>
                            <td>
                        </tr>
                    </table><br>

            <div class="bottom-content">
                <div class="bottom-left">
                    <label class="stick">Дата создания</label><br>
                    <label class="views">${cardout[9]}</label>
                </div>
            </div><br><%--close class = bottom-content--%>
            <a class="link" href="/cardedit?id=${cardout[1]}">Корректировать</a>
            <a class="link" href="/cardcreaterevision?id=${cardout[1]}">Выпустить ревизию</a>
        </form>
    </div>

    </body>
</html>
