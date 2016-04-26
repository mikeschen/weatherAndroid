package com.example.guest.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.guest.weather.R;
import com.example.guest.weather.adapters.WeatherListAdapter;
import com.example.guest.weather.models.Weather;
import com.example.guest.weather.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherListActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private WeatherListAdapter mAdapter;


    public ArrayList<Weather> mWeather = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        Log.d(TAG, location);
        getWeather(location);
    }

    private void getWeather(String location) {

        final WeatherService weatherService = new WeatherService();

        WeatherService.findWeather(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mWeather = weatherService.processResults(response);

                WeatherListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (Weather weather : mWeather) {
                            Log.d("WEATHER", weather.getMaxTemp() + "");
                        }
                        mAdapter = new WeatherListAdapter(WeatherListActivity.this, mWeather);

                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WeatherListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
