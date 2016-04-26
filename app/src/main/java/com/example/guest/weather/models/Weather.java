package com.example.guest.weather.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 4/25/16.
 */

    @Parcel
public class Weather {

    private int mTempMax;
    private int mTempMin;
    private String mDate;

    public Weather(int tempMax, int tempMin, String date) {
        this.mTempMax = tempMax * 9/5 - 460;
        this.mTempMin = tempMin * 9/5 - 460;
        this.mDate = date;
        }

    public int getMaxTemp() {
        return mTempMax;
    }

    public int getMinTemp() {
        return mTempMin;
    }


    public String getDate() {
        return mDate;
    }

    public Weather() {

    }
}