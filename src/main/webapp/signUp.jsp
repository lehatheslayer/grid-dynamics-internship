<%--
  Created by IntelliJ IDEA.
  User: areshetnikov
  Date: 2/21/22
  Time: 5:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Register page</h1>
<p><a href="/">Go to main page</a></p>
<p><a href="signIn">Login</a></p>
<form action="signUp" method="post">
    <label>
        <p>User name:</p>
        <input type="text" name="name" required>
    </label>
    <label>
        <p>Password:</p>
        <input type="password" name="password" id="password" required>
    </label>
    <label>
        <p>Confirm password:</p>
        <input type="password" id="confirm_password" required>
    </label>
    <label>
        <p>Email:</p>
        <input type="email" name="email">
    </label>

    <input type="hidden" name="roleId" value="1">
    <input type="submit">
</form>

<script>
    let password = document.getElementById("password");
    let confirm_password = document.getElementById("confirm_password");

    function validatePassword(){
        if(password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</body>
</html>
