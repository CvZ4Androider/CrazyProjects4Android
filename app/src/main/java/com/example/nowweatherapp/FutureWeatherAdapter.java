package com.example.nowweatherapp;

import static com.example.nowweatherapp.Util.ImageUtil.getImgResOfWeather;
import static com.example.nowweatherapp.Util.WeekUtil.getWeekday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nowweatherapp.bean.DayWeatherBean;

import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.WeatherViewHolder>  {

    private Context mContext;
    private List<DayWeatherBean> mWeatherBeans;

    public FutureWeatherAdapter(Context context, List<DayWeatherBean> WeatherBeans) {
        this.mContext = context;
        this.mWeatherBeans = WeatherBeans;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item_layout, parent, false);
        WeatherViewHolder weatherViewHolder=new WeatherViewHolder(view);
        return weatherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        DayWeatherBean weatherBean=mWeatherBeans.get(position);

        holder.tv_date.setText(weatherBean.getDate());
        holder.tv_week.setText(getWeekday(weatherBean.getDate()));
        holder.iv_weather.setImageResource(getImgResOfWeather(weatherBean.getWeatherImg()));
        holder.tv_weather.setText(weatherBean.getWeather());
        holder.tv_temperature_details.setText(weatherBean.getTemMin()+"℃~"+weatherBean.getTemMax()+"℃");
        holder.tv_wind.setText(weatherBean.getWind()+"  "+weatherBean.getWindSpeed());


    }

    @Override
    public int getItemCount() {
        return (mWeatherBeans == null) ? 0 : mWeatherBeans.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date,tv_week,tv_weather,tv_temperature_details,tv_wind;
        ImageView iv_weather;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_date=itemView.findViewById(R.id.tv_date);
            tv_week=itemView.findViewById(R.id.tv_week);
            tv_weather=itemView.findViewById(R.id.tv_weather);
            tv_temperature_details=itemView.findViewById(R.id.tv_temperature_details);
            tv_wind=itemView.findViewById(R.id.tv_wind);
            iv_weather=itemView.findViewById(R.id.iv_weather);
        }
    }
}
