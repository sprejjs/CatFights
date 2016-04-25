package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vspreys on 24/04/16.
 */
public class GameResult implements Serializable {

    private int id;
    private Player player;
    private Cat winner;
    private Date date;

    public GameResult(Player player, Cat winner) {
        this.player = player;
        this.winner = winner;
        this.date = new Date(); //Current date and time;
    }

    public GameResult(Player player, Cat winner, Date date) {
        this.player = player;
        this.winner = winner;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Cat getWinner() {
        return this.winner;
    }

    public int getWinnerId() {
        return this.winner.getId();
    }

    public int getPlayerId() {
        return this.player.getId();
    }

    public Date getDate() {
        return this.date;
    }

    public String getDateAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(this.date);
    }
}
