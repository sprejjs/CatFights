package servlets;

import model.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vspreys on 24/04/16.
 */
@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    public static final String ATTRIBUTE_PLAYER = "player";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession(true); //Create session if doesn't exist

        Player player = (Player) session.getAttribute(ATTRIBUTE_PLAYER);

        if(player == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
            dispatcher.forward(request, response);
        }
    }
}
