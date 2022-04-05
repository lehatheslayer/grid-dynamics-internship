<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 3/18/22
  Time: 6:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p><a href="/add/book">add book</a></p>
    <c:forEach var="book" items="${books}">
        <div style="width: 300px; border: solid 1px">
            <div style="width: 100%; border-bottom: solid 1px; text-align: center">
                <h2> ${book.getName()} : ${book.getAuthor()} </h2>
            </div>
            <div style="width: 100%; border-bottom: solid 1px">
                <div style="margin-left: 10px">
                    <h3>Описание</h3>
                    <p> ${book.getDescription()}</p>
                    <h3>Цена: ${book.getCost()}</h3>
                </div>
            </div>

            <form:form action="/delete/book" method="post" modelAttribute="book_to_del">
                <form:hidden path="id" value="${book.getId()}"/>
                <input type="submit" value="delete">
            </form:form>
        </div>
    </c:forEach>
</body>
</html>
