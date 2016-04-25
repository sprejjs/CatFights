<%@ page import="model.Round" %>
<%@ page import="static servlets.GameServlet.ATTRIBUTE_ROUND" %>
<%@ page import="model.Cat" %>
<%--
  Created by IntelliJ IDEA.
  User: vspreys
  Date: 25/04/16
  Time: 5:02 PM
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
        Round round = (Round) request.getAttribute(ATTRIBUTE_ROUND);
        Cat catA = round.getParticipantA();
        Cat catB = round.getParticipantB();
    %>

    <br/>
    <h2>Select your favourite cat</h2>
    <div class="competitor" onclick="window.open('/servlets/game?round_winner=a')">
        <h4><%= catA.getName() %></h4>
        <img src="<%= catA.getPhotoPath() %>">
    </div>
    <div class="competitor" onclick="window.open('/servlets/game?round_winner=b')">
        <h4><%= catB.getName() %></h4>
        <img src="<%= catB.getPhotoPath() %>">
    </div>
</body>
</html>
