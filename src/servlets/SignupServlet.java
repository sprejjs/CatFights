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
import static servlets.LoginServlet.ATTRIBUTE_ERROR_MESSAGE;

/**
 * Created by vspreys on 25/04/16.
 */
public class SignupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.length() < 3 || password.length() < 8) {
            req.setAttribute(ATTRIBUTE_ERROR_MESSAGE, "Invalid username or password.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
            dispatcher.forward(req, resp);

            return;
        }

        Player player = DBHelper.getInstance().register(username, password);

        if (player == null) {
            req.setAttribute(ATTRIBUTE_ERROR_MESSAGE, "Failed creating a new user. Does your account already exist?");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
            dispatcher.forward(req, resp);
        } else {
            HttpSession session = req.getSession(true); //Create session if doesn't exist
            session.setAttribute(ATTRIBUTE_PLAYER, player);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/main_menu.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
