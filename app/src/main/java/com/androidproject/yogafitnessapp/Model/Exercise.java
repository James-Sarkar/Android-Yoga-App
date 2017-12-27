package com.androidproject.yogafitnessapp.Model;

/**
 * Created by James Sarkar.
 */

public class Exercise {

    private int imageId;

    private String name;

    public Exercise(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
