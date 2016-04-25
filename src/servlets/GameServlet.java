package servlets;

import model.Cat;
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
    public static final String ATTRIBUTE_ROUND_WINNER = "round_winner";
    public static final String ATTRIBUTE_GAME_WINNER = "game_winner";

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
        String winningCat = req.getParameter(ATTRIBUTE_ROUND_WINNER);
        if (winningCat != null) {
            Round round = game.getNextRound();
            if(winningCat.equals("a")) {
                round.catAWon();
            } else {
                round.catBWon();
            }
        }

        session.setAttribute(ATTRIBUTE_GAME, game);

        Round round = game.getNextRound();

        if(round != null) {
            //The game isn't over yet
            req.setAttribute(ATTRIBUTE_ROUND, round);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/round.jsp");
            dispatcher.forward(req, resp);
        } else {
            //The game is over
            Cat winner = game.getWinner();
            req.setAttribute(ATTRIBUTE_GAME_WINNER, winner);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/winner.jsp");
            dispatcher.forward(req, resp);

            //Reset the game
            session.setAttribute(ATTRIBUTE_GAME, null);
        }
    }
}
