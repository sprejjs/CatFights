package model;

import java.io.Serializable;

/**
 * Created by vspreys on 24/04/16.
 */
public class Player implements Serializable {

    private String username;
    private String password;
    private int id;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this.id;
    }
}
