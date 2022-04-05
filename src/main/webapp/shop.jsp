<%@ page import="java.util.List" %>
<%@ page import="entity.Book" %>
<%@ page import="entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>The list of books</h1>
<% if (session.getAttribute("loginUser") != null) { %>
    <% User user = (User) session.getAttribute("loginUser"); %>
    <p>Hi, <%= user.getName() %></p>
    <p><a href="profile">Profile</a></p>
    <p><a href="logout">Logout</a></p>
    <% if (user.getRoleId() == 2) { %>
        <p><a href="add/book">add book</a></p>
    <% } %>
<% } else { %>
    <p><a href="signUp">Register</a> <a href="signIn">Login</a></p>
<% } %>

<%
    List<Book> books = (List) request.getAttribute("books");
    for (Book book : books) {
%>
    <div style="width: 300px; border: solid 1px">
        <div style="width: 100%; border-bottom: solid 1px; text-align: center">
            <h2> <%= book.getName() %> : <%= book.getAuthor() %></h2>
        </div>
        <div style="width: 100%; border-bottom: solid 1px">
            <div style="margin-left: 10px">
                <h3>Описание</h3>
                <p> <%= book.getDescription() %></p>
                <h3>Цена: <%= book.getCost() %></h3>
            </div>
        </div>
        <div style="width: 100%; text-align: center">
            <form action="delete/book" method="post">
                <label>
                    <input type="hidden" name="id" value="<%= book.getId() %>">
                    <input type="submit" value="Купить" <% if (session.getAttribute("loginUser") == null) { %> disabled <% } %>>
                </label>
            </form>
        </div>
    </div>
<% } %>

</body>
</html>
