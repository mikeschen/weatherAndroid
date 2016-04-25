package com.example.guest.weather.models;

/**
 * Created by Guest on 4/25/16.
 */
public class Weather {
    private int mTempMax;

    public Weather(int tempMax) {
        this.mTempMax = tempMax * 9/5 - 460;
        }

    public int getMaxTemp() {
        return mTempMax;
    }
}