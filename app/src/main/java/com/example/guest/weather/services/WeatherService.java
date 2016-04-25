package com.example.guest.weather.services;

import android.util.Log;

import com.example.guest.weather.Constants;
import com.example.guest.weather.ui.MainActivity;

import java.util.PriorityQueue;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;


/**
 * Created by Guest on 4/25/16.
 */
public class WeatherService {
    public static final String TAG = MainActivity.class.getSimpleName();

    public static void findWeather(String location, Callback callback) {
       String CONSUMER_KEY = Constants.WEATHER_CONSUMER_KEY;
        String WEATHER_URL = Constants.WEATHER_BASE_URL;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WEATHER_LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter("appid", Constants.WEATHER_CONSUMER_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}

