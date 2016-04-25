package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vspreys on 24/04/16.
 */
public class GameResult implements Serializable {

    private int id;
    private int winnerId;
    private int playerId;
    private Date date;

    public GameResult(Player player, Cat winner) {
        this.playerId = player.getId();
        this.winnerId = winner.getId();
        this.date = new Date(); //Current date and time;
    }

    public int getId() {
        return this.id;
    }

    public int getWinnerId() {
        return this.winnerId;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDateAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(this.date);
    }
}
