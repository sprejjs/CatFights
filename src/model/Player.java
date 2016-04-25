package model;

import java.io.Serializable;

/**
 * Created by vspreys on 24/04/16.
 */
public class Player implements Serializable {

    private int id;
    private String username;

    public Player(int id, String username) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this.id;
    }
}
