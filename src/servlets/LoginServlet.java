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

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        DBHelper dbHelper = DBHelper.getInstance();
        boolean loginSuccess = dbHelper.login(username, password);

        PrintWriter printWriter = resp.getWriter();
        if(loginSuccess) {
            printWriter.print("<h1>Login success!</h1>");
        } else {
            printWriter.print("<h1>Login fail!</h1>");
        }
    }
}
