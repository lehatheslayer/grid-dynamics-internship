<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 3/28/22
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Registration</h1>
    <a href="/">Go to main page</a>
    <form:form action="/registration" method="post" modelAttribute="userForm">
        <label>
            <p>First name</p>
            <form:input path="firstName"/>
        </label>
        <label>
            <p>Second name</p>
            <form:input path="secondName"/>
        </label>
        <label>
            <p>login</p>
            <form:input path="login"/>
        </label>
        <label>
            <p>password</p>
            <form:password path="password"/>
        </label>
        <label>
            <p>email</p>
            <form:input path="email"/>
        </label>
        <input type="submit" value="register">
    </form:form>
</body>
</html>
