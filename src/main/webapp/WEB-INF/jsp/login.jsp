<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 3/30/22
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Login</h1>
    <div><a href="/">Go to main page</a></div>
    <div><a href="/registration">Register</a></div>
    <form action="/login" method="POST">

        <label>
            <p>Username</p>
            <input type="text" name="username" required>
        </label>
        <label>
            <p>Password</p>
            <input type="password" name="password" required>
        </label>

        <input type="submit" value="login">
    </form>
</body>
</html>
