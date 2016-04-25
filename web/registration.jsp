<%@ page import="servlets.LoginServlet" %><%--
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
        <%
            String username = "";
            String password = "";
            String errorMessage = (String) request.getAttribute(LoginServlet.ATTRIBUTE_ERROR_MESSAGE);
        %>

        <h1> Welcome to cat fights!</h1>
        <img src="http://thecatapi.com/api/images/get?format=src&type=gif"> <br/>

        <%
            if (errorMessage != null) {
                out.print("<div class=\"error_message\">");
                out.print(errorMessage);
                out.print("</div><br />");
            }
        %>

        <form action="${pageContext.request.contextPath}/servlets/login" id="login_form">
            <h3>Sign in</h3>
            <label for="inp_sign_in_username">Username: </label> <input id="inp_sign_in_username" type="text" name="username" value="<%= username %>"> <br/>
            <label for="inp_sign_in_password">Password: </label> <input id="inp_sign_in_password" type="password" name="password" value="<%= password %>">
            <input type="submit" value="Sign in">
        </form>
        <form action="${pageContext.request.contextPath}/servlets/signup" id="signup_form">
            <h3>Sign up</h3>
            <label for="inp_sign_up_username">Username: </label> <input id="inp_sign_up_username" type="text" name="username" value="<%= username %>"> <br/>
            <label for="inp_sign_up_password">Password: </label> <input id="inp_sign_up_password" type="password" name="password" value="<%= password %>">
            <input type="submit" value="Sign up">
        </form>
    </body>
</html>
