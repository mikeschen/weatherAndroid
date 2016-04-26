package com.example.guest.weather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.weather.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.findWeatherButton)
    Button mFindWeatherButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindWeatherButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindWeatherButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, WeatherListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }
}
