package com.example.nowweatherapp.Util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    private static final String URL_WEATHER_NOW = "https://yiketianqi.com/free/day?unescape=1&appid=31145192&appsecret=8hDSvLht";
    private static final String URL_WEATHER_FUTURE = "https://yiketianqi.com/free/week?unescape=1&appid=31145192&appsecret=8hDSvLht";

    public static String doGet(String urlStr) {
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        //连接网络
        try {
            URL urL = new URL(urlStr);
            connection = (HttpURLConnection) urL.openConnection();
            //网络请求为GET模式，超时时间为5秒
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(12000);

            //从连接中读取数据（获取的是二进制输入流）
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            //二进制流送入缓冲区，缓冲读取提高效率
            br = new BufferedReader(inputStreamReader);
            //一行一行读取字符串
            String line = "";
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //网络请求需要占用很多资源，所以我们完成数据读取后要及时关闭这些工具
            //关闭连接
            if (connection != null) {
                connection.disconnect();
            }
            //关闭输入流inputStreamReader
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭缓冲输入流BufferedReader
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String getCityWeatherNow(String city) {
        //拼接URL，通过这个URL去目标网站获取天气数据
        String weatherUrl = URL_WEATHER_NOW + "&city=" + city;
        String weatherResult = doGet(weatherUrl);
        Log.d("CvZ", "---已从网站获取即刻天气数据:---" + weatherResult);
        return weatherResult;

    }

    public static String getCityWeatherFuture(String city) {
        //拼接URL，通过这个URL去目标网站获取天气数据
        String weatherUrl = URL_WEATHER_FUTURE + "&city=" + city;
        String weatherResult = doGet(weatherUrl);
        Log.d("CvZ", "---已从网站获取七天天气数据:---" + weatherResult);
        return weatherResult;
    }
}
