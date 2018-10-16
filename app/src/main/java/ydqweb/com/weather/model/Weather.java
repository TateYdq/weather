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

    public Weather(HashMap<String,String> hashMap){
        this.quality = hashMap.get("quality");
        this.curPlace = hashMap.get("city");
        this.curTemperature = hashMap.get("wendu");
        this.curHumidity = "今天"+hashMap.get("updatetime")+"发布\n湿度:"+hashMap.get("humidity");
        this.curPm = hashMap.get("pm25");
        this.curWind = hashMap.get("fengli");
        this.curDay = hashMap.get("date1");
        this.curDate2 = hashMap.get("date2");
        this.cur
    }
    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCurPm() {
        return curPm;
    }

    public void setCurPm(String curPm) {
        this.curPm = curPm;
    }

    public String getCurPlace() {
        return curPlace;
    }

    public void setCurPlace(String curPlace) {
        this.curPlace = curPlace;
    }

    private String quality;
    private String curPm;
    private String curPlace;
    private String curWind;

    public String getCurWind() {
        return curWind;
    }

    public void setCurWind(String curWind) {
        this.curWind = curWind;
    }

    public String getCurHumidity() {
        return curHumidity;
    }

    public void setCurHumidity(String curHumidity) {
        this.curHumidity = curHumidity;
    }

    public String getCurDay() {
        return curDay;
    }

    public void setCurDay(String curDay) {
        this.curDay = curDay;
    }

    private String curHumidity;
    private String curDay;

    public String getCurDate2() {
        return curDate2;
    }

    public void setCurDate2(String curDate2) {
        this.curDate2 = curDate2;
    }

    public String getCurDate3() {
        return curDate3;
    }

    public void setCurDate3(String curDate3) {
        this.curDate3 = curDate3;
    }

    public String getCurDate4() {
        return curDate4;
    }

    public void setCurDate4(String curDate4) {
        this.curDate4 = curDate4;
    }

    private String curDate2;
    private String curDate3;
    private String curDate4;

    public String getCurTemperature() {
        return curTemperature;
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
