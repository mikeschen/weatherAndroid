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
    private String mImageUrl;

    public Weather(int tempMax, int tempMin, String date, String imageUrl) {
        this.mTempMax = tempMax * 9/5 - 460;
        this.mTempMin = tempMin * 9/5 - 460;
        this.mDate = date;
        this.mImageUrl = "http://openweathermap.org/img/w/" + imageUrl + ".png";
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

    public String getImageUrl() { return mImageUrl; }

    public Weather() {

    }
}