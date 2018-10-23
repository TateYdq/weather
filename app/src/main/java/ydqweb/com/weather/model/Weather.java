package ydqweb.com.weather.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.locks.LockSupport;


/**
 * Created by yu_daoqing on 2018/10/9.
 */

public class Weather {
    private static String TAG = "Weather";
    private static String mStr="";
    private static Thread th;

    public Weather(String quality, String curPm, String curPlace) {
        this.quality = quality;
        this.curPm = curPm;
        this.curPlace = curPlace;
    }
    private String dealWeahter(String dayWea,String nightWea){
        if(dayWea.equals(nightWea)){
            return dayWea;
        }else{
            return dayWea+"转"+nightWea;
        }
    }
    private String dealFl(String dayFl,String nightFl){
        return dayFl;
    }

    private String dealDate(String day){
        if(day.split("日")[1] != null){
            return day.split("日")[1];
        }else{
            return day;
        }

    }
    public Weather(HashMap<String,String> hashMap) {
        try {
            this.quality = hashMap.get("quality");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            this.curPlace = hashMap.get("city");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            this.curTemperature = hashMap.get("date1TempLow")+"~"+hashMap.get("date1TempHigh");
            Log.i(TAG,"cur:"+curTemperature);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDate2Temp = hashMap.get("date2TempLow")+"~"+hashMap.get("date2TempHigh");
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDate3Temp = hashMap.get("date3TempLow")+"~"+hashMap.get("date3TempHigh");
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDayWeather = dealWeahter(hashMap.get("date1DayWea"),hashMap.get("date1NightWea"));
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDate2Weather = dealWeahter(hashMap.get("date2DayWea"),hashMap.get("date2NightWea"));
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDate3Weather = dealWeahter(hashMap.get("date3DayWea"),hashMap.get("date3NightWea"));
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDate4Weather = dealWeahter(hashMap.get("date4DayWea"),hashMap.get("date4NightWea"));
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }


        try{
            this.curDate4Temp = hashMap.get("date4TempLow")+"~"+hashMap.get("date4TempHigh");
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }


        try {
            this.curHumidity = "今天" + hashMap.get("updatetime") + "发布\n湿度:" + hashMap.get("shidu");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            this.curPm = hashMap.get("pm25");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            //Log.i(TAG,"!!!curDay:"+hashMap.get("date1"));
            this.curDay = dealDate(hashMap.get("date1"));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            this.curDate2 = dealDate(hashMap.get("date2"));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            this.curDate3 = dealDate(hashMap.get("date3"));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            this.curDate4 = dealDate(hashMap.get("date4"));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDate2Wind = dealFl(hashMap.get("date2DayFl"),hashMap.get("date2NightFl"));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDate3Wind = dealFl(hashMap.get("date3DayFl"),hashMap.get("date3NightFl"));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try{
            this.curDate4Wind = dealFl(hashMap.get("date4DayFl"),hashMap.get("date4NightFl"));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            this.curWind = dealFl(hashMap.get("date1DayFl"),hashMap.get("date1NightFl"));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
        public String getQuality() {
            return quality;
        }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCurPm() {
        if(curPm == null){
            return "";
        }else {
            return curPm;
        }
    }

    public void setCurPm(String curPm) {
        this.curPm = curPm;
    }

    public String getCurPlace() {
        if(curPlace == null){
            return "";
        }else {
            return curPlace;
        }
    }

    public void setCurPlace(String curPlace) {
        this.curPlace = curPlace;
    }

    private String quality;
    private String curPm;
    private String curPlace;
    private String curWind;

    public String getCurWind() {
        if(curWind == null){
            return "";
        }else {
            return curWind;
        }
    }

    public void setCurWind(String curWind) {
        this.curWind = curWind;
    }

    public String getCurHumidity() {
        if(curHumidity == null){
            return "";
        }else {
            return curHumidity;
        }
    }

    public void setCurHumidity(String curHumidity) {
        this.curHumidity = curHumidity;
    }

    public String getCurDay() {
        if(curDay == null){
            return "";
        }else {
            return curDay;
        }
    }

    public void setCurDay(String curDay) {
        this.curDay = curDay;
    }

    private String curHumidity;
    private String curDay;

    public String getCurDate2() {
        if(curDate2 == null){
            return "";
        }else {
            return curDate2;
        }
    }

    public void setCurDate2(String curDate2) {
        this.curDate2 = curDate2;
    }

    public String getCurDate3() {
        if(curDate3 == null){
            return "";
        }
        return curDate3;
    }

    public void setCurDate3(String curDate3) {
        this.curDate3 = curDate3;
    }

    public String getCurDate4() {
        if(curDate4 == null){
            return "";
        }else {
            return curDate4;
        }
    }

    public void setCurDate4(String curDate4) {
        this.curDate4 = curDate4;
    }

    private String curDate2;

    public String getCurDate2Wind() {
        return curDate2Wind;
    }

    public void setCurDate2Wind(String curDate2Wind) {
        this.curDate2Wind = curDate2Wind;
    }

    public String getCurDate2Temp() {
        return curDate2Temp;
    }

    public void setCurDate2Temp(String curDate2Temp) {
        this.curDate2Temp = curDate2Temp;
    }

    public String getCurDate2Weather() {
        return curDate2Weather;
    }

    public void setCurDate2Weather(String curDate2Weather) {
        this.curDate2Weather = curDate2Weather;
    }

    public String getCurDate3Wind() {
        return curDate3Wind;
    }

    public void setCurDate3Wind(String curDate3Wind) {
        this.curDate3Wind = curDate3Wind;
    }

    public String getCurDate3Temp() {
        return curDate3Temp;
    }

    public void setCurDate3Temp(String curDate3Temp) {
        this.curDate3Temp = curDate3Temp;
    }

    public String getCurDate3Weather() {
        return curDate3Weather;
    }

    public void setCurDate3Weather(String curDate3Weather) {
        this.curDate3Weather = curDate3Weather;
    }

    public String getCurDate4Wind() {
        return curDate4Wind;
    }

    public void setCurDate4Wind(String curDate4Wind) {
        this.curDate4Wind = curDate4Wind;
    }

    public String getCurDate4Temp() {
        return curDate4Temp;
    }

    public void setCurDate4Temp(String curDate4Temp) {
        this.curDate4Temp = curDate4Temp;
    }

    public String getCurDate4Weather() {
        return curDate4Weather;
    }

    public void setCurDate4Weather(String curDate4Weather) {
        this.curDate4Weather = curDate4Weather;
    }

    private String curDate2Wind;
    private String curDate2Temp;

    public String getCurDayWeather() {
        return curDayWeather;
    }

    public void setCurDayWeather(String curDayWeather) {
        this.curDayWeather = curDayWeather;
    }

    private String curDayWeather,curDate2Weather;
    private String curDate3,curDate3Wind,curDate3Temp,curDate3Weather;
    private String curDate4,curDate4Wind,curDate4Temp,curDate4Weather;

    public String getCurTemperature() {
        if (curTemperature == null) {
            return "";
        }else {
            return curTemperature;
        }
    }

    public void setCurTemperature(String curTemperature) {
        this.curTemperature = curTemperature;
    }

    private String curTemperature;



    public static String getWeather(final String cityName){
        th = Thread.currentThread();
        new Thread(new Runnable(){
            @Override
            public void run(){
                HttpURLConnection conn = null;
                try {
                    URL cityUrl = new URL(Constant.CITY_NAME + cityName);
                    //URL cityUrl = new URL(Constant.CITY_KEY + "101010100");
                    conn = (HttpURLConnection)cityUrl.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    InputStream is = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    StringBuilder response = new StringBuilder();
                    String str;
                    while((str = br.readLine())!=null){
                        response.append(str);
                    }
                    String res = response.toString();
                    mStr = res;
                    Log.i(TAG,"response:"+res);
                }catch (Exception e){
                    Log.e(TAG,e.getMessage());
                }
                finally {
                    if(conn != null){
                        conn.disconnect();
                    }
                    LockSupport.unpark(th);
                }

            }
        }).run();


        LockSupport.park();
        return mStr;
    }

}
