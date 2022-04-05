<%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 2/21/22
  Time: 3:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Adding book page</h1>
    <a href="/">Go to main page</a>
    <form action="add/book" method="post">
        <label>
            <p>Book name:</p>
            <input type="text" name="name">
        </label>
        <label>
            <p>Description:</p>
            <input type="text" name="description">
        </label>
        <label>
            <p>Author:</p>
            <input type="text" name="author">
        </label>
        <label>
            <p>Cost:</p>
            <input type="text" name="cost">
        </label>

        <input type="submit">
    </form>
</body>
</html>
