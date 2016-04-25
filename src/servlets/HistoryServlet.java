package servlets;

import model.DBHelper;
import model.GameResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vspreys on 25/04/16.
 */
public class HistoryServlet extends HttpServlet {
    public final static String ATTRIBUTE_GAME_RESULTS = "game_results";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<GameResult> results = DBHelper.getInstance().getGameResults();

        req.setAttribute(ATTRIBUTE_GAME_RESULTS, results);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}
