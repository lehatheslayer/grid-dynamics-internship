<%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 2/21/22
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Failed page</h1>
    <a href="/">Go to main page</a>
    <h2>Message:</h2>
    <p> <%= request.getAttribute("message") %></p>
</body>
</html>
