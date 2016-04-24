package servlets;

import model.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vspreys on 25/04/16.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        DBHelper dbHelper = DBHelper.getInstance();

        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<h1>Login servlet called with username " + req.getParameter("username") + "</h1>");
    }
}
