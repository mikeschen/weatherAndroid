package com.example.guest.weather.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 4/25/16.
 */

    @Parcel
public class Weather {


    private int mTempMax;

    public Weather(int tempMax) {
        this.mTempMax = tempMax * 9/5 - 460;
        }

    public int getMaxTemp() {
        return mTempMax;
    }

    public Weather() {

    }
}