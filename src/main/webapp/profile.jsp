<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 2/22/22
  Time: 11:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<% User user = (User) session.getAttribute("loginUser"); %>
<h1>Profile of <%= user.getName() %></h1>
<p><a href="/">Go to main page</a></p>
<p><a href="logout">Logout</a></p>

<p>Email: <%= user.getEmail()%></p>
<p>Role: <% if (user.getRoleId() == 1) { %> User <% } else { %> Admin <% } %></p>
</body>
</html>
