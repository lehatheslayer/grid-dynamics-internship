<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 3/21/22
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddBook</title>
</head>
<body>
    <h1>Adding book page</h1>
    <a href="/">Go to main page</a>
    <form:form action="/add/book" method="post" modelAttribute="bookForm">
        <label>
            <p>Name:</p>
            <form:input path="name"/>
        </label>
        <label>
            <p>description</p>
            <form:textarea path="description"/>
        </label>
        <label>
            <p>author</p>
            <form:input path="author"/>
        </label>
        <label>
            <p>cost</p>
            <form:input path="cost"/>
        </label>
        <input type="submit" value="add">
    </form:form>
</body>
</html>
