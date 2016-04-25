package servlets;

import model.DBHelper;
import model.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static servlets.IndexServlet.ATTRIBUTE_PLAYER;

/**
 * Created by vspreys on 25/04/16.
 */
public class LoginServlet extends HttpServlet {
    public static final String ATTRIBUTE_ERROR_MESSAGE = "message";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        DBHelper dbHelper = DBHelper.getInstance();
        boolean loginSuccess = dbHelper.login(username, password);

        if (!loginSuccess) {
            req.setAttribute(ATTRIBUTE_ERROR_MESSAGE, "Invalid username or password");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
            dispatcher.forward(req, resp);
        } else {
            Player player = new Player(username, password);

            HttpSession session = req.getSession(true); //Create session if doesn't exist\
            session.setAttribute(ATTRIBUTE_PLAYER, player);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main_menu.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
