<%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 2/21/22
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Login page</h1>
    <p><a href="/">Go to main page</a></p>
    <p><a href="signUp">Register</a></p>

    <form action="signIn" method="post">
        <label>
            <p>UserName:</p>
            <input type="text" name="name" required>
        </label>
        <label>
            <p>Password:</p>
            <input type="password" name="password" required>
        </label>

        <input type="submit">
    </form>
</body>
</html>
