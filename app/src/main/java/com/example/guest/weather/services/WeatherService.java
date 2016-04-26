package com.example.guest.weather.services;

import android.util.Log;

import com.example.guest.weather.Constants;
import com.example.guest.weather.models.Weather;
import com.example.guest.weather.ui.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
        urlBuilder.addQueryParameter(Constants.WEATHER_SEVEN_DAY_PARAMETER, "7");
        urlBuilder.addQueryParameter("appid", Constants.WEATHER_CONSUMER_KEY);
        String url = urlBuilder.build().toString();
        Log.d(TAG, url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weathers = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject weatherJSON = new JSONObject(jsonData);
                JSONArray listJSON = weatherJSON.getJSONArray("list");
                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject dayJSON = listJSON.getJSONObject(i);
                    int maxTemp = dayJSON.getJSONObject("temp").getInt("max");
                    int minTemp = dayJSON.getJSONObject("temp").getInt("min");
                    Log.d(TAG, "testData" + dayJSON);
                    long dateJSON = (dayJSON.getLong("dt") * 1000);

                    String date;
                    SimpleDateFormat df = new SimpleDateFormat("EEEE");
                    date = df.format(dateJSON);

                    Weather weather = new Weather(maxTemp, minTemp, date);
                    weathers.add(weather);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weathers;
    }
}
