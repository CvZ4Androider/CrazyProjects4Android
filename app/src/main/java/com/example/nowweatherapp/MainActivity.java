package com.example.nowweatherapp;

import static com.example.nowweatherapp.Util.CityListUtil.getAllCities;
import static com.example.nowweatherapp.Util.ImageUtil.getImgResOfWeather;
import static com.example.nowweatherapp.Util.NetUtil.getCityWeatherNow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nowweatherapp.Util.NetUtil;
import com.example.nowweatherapp.bean.DayWeatherBean;
import com.example.nowweatherapp.bean.FutureWeatherBean;
import com.example.nowweatherapp.bean.NowWeatherBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private AppCompatSpinner mSpinner;
    private ArrayList<String> mCities;
    private ArrayAdapter<String> mAdapter;
    private HashSet<String> ALL_CITIES;
    private Button btn_add_city;
    private TextView tv_temperature;
    private TextView tv_weather;
    private TextView tv_temperature_details;
    private TextView tv_wind;
    private TextView tv_air;
    private TextView tv_advice;
    private ImageView iv_weather;
    private RecyclerView rlv_future_weather;
    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            //判断接收到的消息是不是来源合适的线程
            //what=0代表即刻天气数据
            if (message.what == 0) {
                String weather = (String) message.obj;
                //用Gson库自动从字符串解析json数据生成一个实体
                Gson gson = new Gson();

                NowWeatherBean nowWeather = gson.fromJson(weather, NowWeatherBean.class);
                Log.d("CvZ", "---主线程收到并解析后的即刻天气数据:---" + nowWeather.toString());
                updateUINowWeather(nowWeather);

            }
            //what=7代表未来七天天气数据
            if (message.what == 7) {
                String weather = (String) message.obj;
                //用Gson库自动从字符串解析json数据生成一个实体
                Gson gson = new Gson();

                FutureWeatherBean futureWeather = gson.fromJson(weather, FutureWeatherBean.class);
                List<DayWeatherBean> sevenDayWeather = futureWeather.getSevenDayWeather();
                Log.d("CvZ", "---主线程收到并解析后的七天天气数据:---" + sevenDayWeather.toString());

                updateUIFutureWeather(sevenDayWeather);

            }
        }
    };

    //更新当前天气的UI界面（主模块）
    @SuppressLint("SetTextI18n")
    private void updateUINowWeather(NowWeatherBean nowWeather) {
        if (nowWeather == null) {
            return;
        }
        iv_weather.setImageResource(getImgResOfWeather(nowWeather.getWeatherImage()));
        tv_temperature.setText(nowWeather.getTemperature() + "℃");
        tv_weather.setText(nowWeather.getWeather() + "（" + nowWeather.getDate() + nowWeather.getWeek() + "）");
        tv_temperature_details.setText(nowWeather.getTemMin() + "℃~" + nowWeather.getTemMax() + "℃");
        tv_wind.setText(nowWeather.getWind());
        int air = Integer.parseInt(nowWeather.getAir());
        tv_air.setText("空气指数：" + air + " " + airLevel(air));
        tv_advice.setText(airAdvice(air));

    }

    private void updateUIFutureWeather(List<DayWeatherBean> weatherBeans) {
        //传进来的List中包含了今天在内的七天天气，先把今天的删去
        weatherBeans.remove(0);
        FutureWeatherAdapter weatherAdapter = new FutureWeatherAdapter(this, weatherBeans);
        rlv_future_weather.setAdapter(weatherAdapter);
        //需要调整底部视图的线性布局方式为水平，所以给它设置LinearLayoutManager属性，以及拒绝反转
        LinearLayoutManager linearManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlv_future_weather.setLayoutManager(linearManager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        initView();
    }

    //初始化页面：这是开发过程中的一个好习惯，不要把初始化的内容直接写到onCreate里面，以便于后期维护更新
    private void initView() {
        //获取页面上的组件并设为全局变量
        tv_temperature = findViewById(R.id.tv_temperature);
        tv_weather = findViewById(R.id.tv_weather);
        tv_temperature_details = findViewById(R.id.tv_temperature_details);
        tv_wind = findViewById(R.id.tv_wind);
        tv_air = findViewById(R.id.tv_air);
        tv_advice = findViewById(R.id.tv_advice);
        iv_weather = findViewById(R.id.iv_weather);
        rlv_future_weather = findViewById(R.id.rlv_future_weather);
        btn_add_city = findViewById(R.id.btn_add_city);
        btn_add_city.setOnClickListener(this);
        mSpinner = findViewById(R.id.sp_city);
        mCities = new ArrayList<>();
        Collections.addAll(mCities, getResources().getStringArray(R.array.cities));
        mAdapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, mCities);
        mSpinner.setAdapter(mAdapter);
        ALL_CITIES = new HashSet<>();
        ALL_CITIES = getAllCities();
        Log.d("CITY",ALL_CITIES.toString());
        //下拉列表被选择的监听事件
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nowCity = mCities.get(position);

                getCityNowWeather(nowCity);
                getCityFutureWeather(nowCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //按钮添加城市点击后，弹出对话框要求用户输入城市名并提示输入格式，若合法则将城市名添加到下拉列表第一项
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_city) {
            addCityDialog();

        }
    }

    //弹出对话框以添加城市的方法
    public void addCityDialog() {
        // 创建 City Selection Dialog 对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加一个新城市");
        //提示用户输入字符串的格式
        builder.setMessage("请输入你要添加的城市名，不要带“市”或“区”、“县”等");
        //添加 EditText 控件，让用户可以输入城市名
        EditText editText = new EditText(this);
        builder.setView(editText);
        //添加“确定”按钮和“取消”按钮
        builder.setPositiveButton("确认", (dialog, which) -> {
            //获取用户输入的城市名
            String newCity = editText.getText().toString().trim();
            //如果输入的城市已经在mAdapter中有不需要新添加
            if (mCities.contains(newCity)) {
                mSpinner.setSelection(mCities.indexOf(newCity));
            }
            else {
                //如果输入的城市是真实存在的国内的城市
                if (ALL_CITIES.contains(newCity+"市")) {
                    mCities.add(0,newCity);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, mCities);
                    mSpinner.setAdapter(adapter);
                }//如果输入了错误的城市名称，即国内不存在这个名称的城市或者程序不支持
                else {
                    Toast.makeText(mContext, "请输入正确的城市名", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //用户点击了“取消”按钮，这时候不需要进行任何处理
        builder.setNegativeButton("取消", (dialog, which) -> {

        });
        // 显示对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //根据选择的城市名字获取具体即刻天气信息的方法
    private void getCityNowWeather(String nowCity) {
        //开辟子线程，网络请求
        new Thread(new Runnable() {//这里不用lambda显得逻辑清晰一点
            @Override
            public void run() {
                //请求网络，用写好的NetUtil工具类
                String cityWeather = getCityWeatherNow(nowCity);
                //这里是子线程拿到了数据，要传给主线程去重设UI，因为子线程不能修改UI
                //从消息池中获取一个消息，不new一个消息是为了对象重用，避免资源浪费导致内存泄漏
                Message message = Message.obtain();
                //标记消息以便区分线程来源：即刻天气
                message.what = 0;
                //指定消息的实体
                message.obj = cityWeather;
                //把包装好的消息发出去
                mHandler.sendMessage(message);
            }
        }).start();
    }

    //根据选择的城市名字获取未来七天天气信息的方法
    private void getCityFutureWeather(String nowCity) {
        //开辟子线程，做网络请求
        new Thread(new Runnable() {//这里不用lambda显得逻辑清晰一点
            @Override
            public void run() {
                //请求网络，用写好的NetUtil工具类
                String cityWeather = NetUtil.getCityWeatherFuture(nowCity);
                //这里是子线程拿到了数据，要传给主线程去重设UI，因为子线程不能修改UI
                //从消息池中获取一个消息，不new一个消息是为了对象重用，避免资源浪费导致内存泄漏
                Message message = Message.obtain();
                //标记消息以便区分线程来源：七天天气
                message.what = 7;
                //指定消息的实体
                message.obj = cityWeather;
                //把包装好的消息发出去
                mHandler.sendMessage(message);
            }
        }).start();
    }

    //根据空气指数获取空气等级的方法
    private String airLevel(int air) {
        if (air < 50)
            return "优";
        else if (air < 100) {
            return "良";
        } else if (air < 150) {
            return "轻度污染";
        } else if (air < 200) {
            return "中度污染";
        } else if (air < 300) {
            return "重度污染";
        } else {
            return "严重污染";
        }
    }

    //根据空气指数获取空气建议的方法
    private String airAdvice(int air) {
        if (air < 50)
            return "各类人群可多参加户外活动，多呼吸一下清新的空气。";
        else if (air < 100) {
            return "除少数对某些污染物特别容易过敏的人群外，其他人群可以正常进行室外活动。";
        } else if (air < 150) {
            return "儿童、老年人及心脏病、呼吸系统疾病患者应尽量减少体力消耗大的户外活动。";
        } else if (air < 200) {
            return "儿童、老年人及心脏病、呼吸系统疾病患者应尽量减少外出，停留在室内，一般人群应适量减少户外运动。";
        } else if (air < 300) {
            return "儿童、老年人及心脏病、肺病患者应停留在室内，停止户外运动，一般人群尽量减少户外运动。";
        } else {
            return "儿童、老年人和病人应停留在室内，避免体力消耗，除有特殊需要的人群外，一般人群尽量不要停留在室外。";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //处理new出来的数据和消息队列等，避免内存泄漏
        mContext = null;
        mHandler.removeCallbacksAndMessages(null);
        mAdapter = null;
        ALL_CITIES =null;
    }
}
