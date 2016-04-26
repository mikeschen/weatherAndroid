package com.example.guest.weather.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.weather.models.Weather;
import com.example.guest.weather.ui.WeatherDetailFragment;

import java.util.ArrayList;

/**
 * Created by Guest on 4/26/16.
 */
public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Weather> mWeather;

    public WeatherPagerAdapter(FragmentManager fm, ArrayList<Weather> weathers) {
        super(fm);
        mWeather = weathers;
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherDetailFragment.newInstance(mWeather.get(position));
    }

    @Override
    public int getCount() {
        return mWeather.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mWeather.get(position).getMaxTemp() + "";
    }
}
