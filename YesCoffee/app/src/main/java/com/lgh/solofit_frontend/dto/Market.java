package com.lgh.solofit_frontend.dto;

public class Market {

    public String marketName;
    public int time;
    public int imageResId;

    public Market(String marketName, int time, int imageResId) {
        this.marketName = marketName;
        this.time = time;
        this.imageResId = imageResId;
    }

}
