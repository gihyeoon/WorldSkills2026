package com.lgh.x_frenchtravel;

public class Hotel {

    public int id;
    public String name;
    public float rating;
    public String distance;
    public int imageResId;

    public Hotel(int id, String name, float rating, String distance, int imageResId) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.distance = distance;
        this.imageResId = imageResId;
    }

}
