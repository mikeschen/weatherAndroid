package com.example.guest.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guest.weather.R;
import com.example.guest.weather.models.Weather;
import com.example.guest.weather.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public ArrayList<Weather> mWeathers = new ArrayList<>();
    @Bind(R.id.listView)
    ListView mListView;

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
                mWeathers = weatherService.processResults(response);

                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] weatherMaxes = new String[mWeathers.size()];
                        for (int i = 0; i < weatherMaxes.length; i++) {
                            weatherMaxes[i] = String.valueOf(mWeathers.get(i).getMaxTemp());
                        }

                        ArrayAdapter adapter = new ArrayAdapter(WeatherActivity.this, android.R.layout.simple_list_item_1, weatherMaxes);
                        mListView.setAdapter(adapter);

                        for (Weather weather : mWeathers) {
                            Log.d(TAG, "maxTemp" + weather.getMaxTemp());
                        }
                    }
                });
            }
        });
    }
}
