<%@ page import="model.Player" %>
<%@ page import="servlets.IndexServlet" %><%--
  Created by IntelliJ IDEA.
  User: vspreys
  Date: 25/04/16
  Time: 4:02 PM
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
        Player currentPlayer = (Player) session.getAttribute(IndexServlet.ATTRIBUTE_PLAYER);

        if (currentPlayer == null) {
            RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/");
            dispatcher.forward(request, response);
        }
    %>

    <br/>
    <h1><%= currentPlayer.getUsername() %>, select your action!</h1>

    <div id="screen-select-action">
        <div id="btn_open_room" class="btn-select-action" onclick="window.location.href = '/servlets/game'">
            Start a new game
        </div>

        <div id="btn_closed_room" class="btn-select-action">
            View previous games
        </div>
    </div>
</body>
</html>
