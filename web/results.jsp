<%@ page import="model.GameResult" %>
<%@ page import="java.util.List" %>
<%@ page import="servlets.HistoryServlet" %><%--
  Created by IntelliJ IDEA.
  User: vspreys
  Date: 25/04/16
  Time: 8:17 PM
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
        List<GameResult> gameResults = (List<GameResult>) request.getAttribute(HistoryServlet.ATTRIBUTE_GAME_RESULTS);
    %>

    <table>
        <tr>
            <th>Player Name</th>
            <th>Date</th>
            <th>Winning cat</th>
        </tr>

        <%
            for (GameResult result : gameResults) {
                out.print("<tr>");
                out.print("<td>" + result.getPlayer().getUsername() + "</td>");
                out.print("<td>" + result.getDateAsString() + "</td>");
                out.print("<td>");
                    out.print("<div class='competitor small'>");
                        out.print("<h4>" + result.getWinner().getName() + "</h4>");
                        out.print("<img src='" + result.getWinner().getPhotoPath() + "'>");
                    out.print("</div>");
                out.print("</td>");
                out.print("</tr>");
            }
        %>
    </table>

</body>
</html>
