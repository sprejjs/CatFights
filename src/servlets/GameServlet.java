package servlets;

import model.DBHelper;
import model.Game;
import model.Round;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vspreys on 25/04/16.
 */
public class GameServlet extends HttpServlet {
    public static final String ATTRIBUTE_GAME = "game";
    public static final String ATTRIBUTE_ROUND = "round";
    public static final String ATTRIBUTE_WINNING_CAT = "winner";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Check if the game is already in progress
        HttpSession session = req.getSession();
        Game game = (Game) session.getAttribute(ATTRIBUTE_GAME);

        if (game == null) {
            //Start a new game if it was not
            game = new Game(DBHelper.getInstance().getCats());
        }

        //Save the results of the previous round (if any)
        String winningCat = req.getParameter(ATTRIBUTE_WINNING_CAT);
        if (winningCat != null) {
            System.out.println("Winning cat supplied. It is - " + winningCat);
        }

        session.setAttribute(ATTRIBUTE_GAME, game);

        Round round = game.getNextRound();
        req.setAttribute(ATTRIBUTE_ROUND, round);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/round.jsp");
        dispatcher.forward(req, resp);
    }
}
