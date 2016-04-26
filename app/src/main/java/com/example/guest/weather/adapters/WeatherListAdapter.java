package com.example.guest.weather.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.weather.R;
import com.example.guest.weather.models.Weather;
import com.example.guest.weather.ui.WeatherDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/25/16.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private ArrayList<Weather> mWeather = new ArrayList<>();
    private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<Weather> weathers) {
        mContext = context;
        mWeather = weathers;
    }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position) {
        holder.bindWeather(mWeather.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("test1", mWeather.size() + "");
        return mWeather.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dateTextView) TextView mDateTextView;
        @Bind(R.id.weatherImageView) ImageView mWeatherImageView;
        @Bind(R.id.weatherMaxTextView) TextView mMaxTextView;
        @Bind(R.id.weatherMinTextView) TextView mMinTextView;
        private Context mContext;


        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                        public void onClick(View v) {
                            int itemPosition = getLayoutPosition();
                            Intent intent = new Intent(mContext, WeatherDetailActivity.class);
                            intent.putExtra("position", itemPosition + "");
                            intent.putExtra("weather", Parcels.wrap(mWeather));
                            mContext.startActivity(intent);
                }
            });
        }

        public void bindWeather(Weather weather) {
            mDateTextView.setText(weather.getDate());
            mMaxTextView.setText("High: " + weather.getMaxTemp());
            mMinTextView.setText("Low: " + weather.getMinTemp());
        }
    }
}
