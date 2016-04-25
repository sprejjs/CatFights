<%@ page import="model.Player" %>
<%@ page import="model.Cat" %>
<%@ page import="static servlets.IndexServlet.ATTRIBUTE_PLAYER" %>
<%@ page import="static servlets.GameServlet.ATTRIBUTE_GAME_WINNER" %><%--
  Created by IntelliJ IDEA.
  User: vspreys
  Date: 25/04/16
  Time: 6:26 PM
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
        Player player = (Player) session.getAttribute(ATTRIBUTE_PLAYER);
        Cat winner = (Cat) request.getAttribute(ATTRIBUTE_GAME_WINNER);
    %>

    <br/>
    <h2>The game is over, <%= player.getUsername() %>! Here is the winner!</h2>
    <div class="competitor winner">
        <h3><%= winner.getName() %></h3>
        <img src="<%= winner.getPhotoPath() %>">
    </div>

    <div class="small_button" onclick="window.location.href = '/main_menu.jsp'">
        Back to menu
    </div>
</body>
</html>
