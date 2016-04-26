package com.example.guest.weather.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.weather.R;
import com.example.guest.weather.models.Weather;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherDetailFragment extends Fragment {

    @Bind(R.id.dateTextView) TextView mDateTextView;
    @Bind(R.id.weatherMaxTextView) TextView mWeatherMaxTextView;

    private Weather mWeather;

    public static WeatherDetailFragment newInstance(Weather weather) {
        WeatherDetailFragment weatherDetailFragment = new WeatherDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("weather", Parcels.wrap(weather));
        weatherDetailFragment.setArguments(args);
        return weatherDetailFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeather = Parcels.unwrap(getArguments().getParcelable("weather"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        mDateTextView.setText((mWeather.getDate()));
        mWeatherMaxTextView.setText((mWeather.getMaxTemp() + " High"));
        return view;
    }

}
