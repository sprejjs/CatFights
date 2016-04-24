<%--
  Created by IntelliJ IDEA.
  User: vspreys
  Date: 24/04/16
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to cat fights!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
</head>
<body>
<h1> Welcome to cat fights!</h1>
<img src="http://thecatapi.com/api/images/get?format=src&type=gif"> <br/>
<div id="login_form">
    <h3>Sign in</h3>
    <label for="inp_sign_in_username">Username: </label> <input id="inp_sign_in_username" type="text"> <br />
    <label for="inp_sign_in_password">Password: </label> <input id="inp_sign_in_password" type="password">
    <input type="submit" value="Sign in">
</div>
<div id="signup_form">
    <h3>Sign up</h3>
    <label for="inp_sign_up_username">Username: </label> <input id="inp_sign_up_username" type="text"> <br />
    <label for="inp_sign_up_password">Password: </label> <input id="inp_sign_up_password" type="password">
    <input type="submit" value="Sign up">
</div>
</body>
</html>
