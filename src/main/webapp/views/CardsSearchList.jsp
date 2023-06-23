<%@ page import="java.util.List" %>
<%@ page import="app.card.model.Records"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Результат поиска</title>
        <style> <%@include file="/resources/css/all.css" %> </style>
        <style> <%@include file="/resources/css/cardsearchlist.css" %> </style>
    </head>

    <body>
        <a href="/cardssearch">Назад</a>
        <a href="/menuUser">Главная страница</a><br>

    <%
        List<Records> cardlistsearch;
        cardlistsearch = (List<Records>)request.getAttribute("cardlist");
        if (cardlistsearch !=null && cardlistsearch.size()>0){
    %>
        <div class="wrapper">
    <h2>Результат поиска</h2>
            <%
                if(request.getAttribute("counter") != null){
                    String Message = (String) request.getAttribute("counter");
                    out.println(Message);
                }
            %>
            <br>
            <table class="cardssearchlist">

                <%
                    for (Records s : cardlistsearch){
                %>

                <tr>
                    <td class="anons"><%=s.getTitle()%></td>
                </tr>
                <tr>
                    <td><label>"<%=s.getTitleMore()%>"</label></td>
                </tr>
                <tr>
                    <td class="inf">ID<%=s.getID()%> | rev.<%=s.getRevision()%> | <%=s.getData()%></td>
                </tr>
                <tr>
                    <td>
                        <a href="/cardout?id=<%=s.getID()%>">Подробнее...</a>
                        <a href="/cardedit?id=<%=s.getID()%>">Корректировать</a>
                        <a href="/cardcreaterevision?id=<%=s.getID()%>">Выпустить ревизию</a><br><br>
                    </td>
                </tr>
                <%
                    }
        } else out.println("Ничего не найдено!");
                %>
            </table>
        </div>
    </body>
</html>
