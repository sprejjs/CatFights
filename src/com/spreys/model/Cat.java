package com.spreys.model;

import java.io.Serializable;

/**
 * Created by vspreys on 24/04/16.
 */
public class Cat implements Serializable {

    private int id;
    private String name;
    private String photoPath;

    public Cat(String name, String photoPath) {
        this.name = name;
        this.photoPath = photoPath;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPhotoPath() {
        return this.photoPath;
    }
}
