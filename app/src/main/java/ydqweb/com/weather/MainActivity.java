package ydqweb.com.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

import ydqweb.com.weather.model.Constant;
import ydqweb.com.weather.model.Weather;
import ydqweb.com.weather.model.XML;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private TextView curTemperatureTv, curPlaceTv,qualityTv,curHumidityTv,curDayTv,curWeatherTv,curWindTv,curPmTv;
    private TextView curDate2Tv,curDate3Tv,curDate4Tv,curDate3TempTv,curDate2TempTv,curDate4TempTv,curDate2WeatherTv,curDate3WeatherTv,curDate4WeatherTv;
    private TextView curDate2WindTv,curDate3WindTv,curDate4WindTv,weatherTitleTv;
    private ImageButton refreshBtn,shareBtn,getLocationBtn;
    private Weather mWeather;
    private static final int GET_VALUE = 1;
    private String cityName,cityCode;
    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        Toolbar toolbar =  (Toolbar) findViewById(R.id.main_toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences("AppPref", Context.MODE_PRIVATE);
        cityName = sharedPreferences.getString("cityName","");
        cityCode = sharedPreferences.getString("cityCode","");
        curPlaceTv = (TextView)findViewById(R.id.cur_place);
        curTemperatureTv = (TextView)findViewById(R.id.cur_temperature);
        curHumidityTv = (TextView)findViewById(R.id.cur_humidity);
        curDayTv = (TextView)findViewById(R.id.cur_day);
        curWeatherTv = (TextView)findViewById(R.id.cur_weather);
        curWindTv = (TextView)findViewById(R.id.cur_wind);
        qualityTv = (TextView)findViewById(R.id.cur_quality);
        curPmTv = (TextView)findViewById(R.id.cur_pm);

        weatherTitleTv = (TextView)findViewById(R.id.weather_title_tv);

        refreshBtn = (ImageButton)findViewById(R.id.refresh_weather);
        refreshBtn.setOnClickListener(new MyButtonListener());

        getLocationBtn = (ImageButton)findViewById(R.id.get_location);
        getLocationBtn.setOnClickListener(new MyButtonListener());

        shareBtn = (ImageButton)findViewById(R.id.share_weather);
        shareBtn.setOnClickListener(new MyButtonListener());

        curDate2TempTv = (TextView)findViewById(R.id.cur_date2_temp);
        curDate2Tv = (TextView)findViewById(R.id.cur_date2);
        curDate2WeatherTv = (TextView)findViewById(R.id.cur_date2_weather);
        curDate2WindTv = (TextView)findViewById(R.id.cur_date2_wind);

        curDate3TempTv = (TextView)findViewById(R.id.cur_date3_temp);
        curDate3Tv = (TextView)findViewById(R.id.cur_date3);
        curDate3WeatherTv = (TextView)findViewById(R.id.cur_date3_weather);
        curDate3WindTv = (TextView)findViewById(R.id.cur_date3_wind);

        curDate4TempTv = (TextView)findViewById(R.id.cur_date4_temp);
        curDate4Tv = (TextView)findViewById(R.id.cur_date4);
        curDate4WeatherTv = (TextView)findViewById(R.id.cur_date4_weather);
        curDate4WindTv = (TextView)findViewById(R.id.cur_date4_wind);

        //toolbar.setTitle("北京天气");
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dynamicInit();
    }

    void staticInit(){
        curPlaceTv.setText("北京");
        curTemperatureTv.setText("今天18:25发布\n湿度:57%");
    }

    void dynamicInit(){
        //curPlaceTv.setText();
    }
    public void sendMsg(){
    }
    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET_VALUE:{

                    break;
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Log.i(TAG,"resultCode:"+resultCode);
        if(resultCode == Constant.GET_CITY){
            SharedPreferences sharedPreferences = getSharedPreferences("AppPref", Context.MODE_PRIVATE);
            cityName = sharedPreferences.getString("cityName","");
            cityCode = sharedPreferences.getString("cityCode","");
            Log.i(TAG,"cityName:"+cityName);
        }
    }


    public class MyAsyncTask extends AsyncTask<String,Integer,HashMap<String,String>>{
        @Override
        public void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        public HashMap<String,String> doInBackground(String... params){
            String mStr = Weather.getWeather(params[0]);
            Log.i(TAG,"mStr:"+mStr);
            HashMap<String,String> hashMap = XML.dealXML(mStr);
            Log.i(TAG,hashMap.toString());
            return hashMap;
        }
        @Override
        public void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
        }
        @Override
        public void onPostExecute(HashMap<String,String> result){
            super.onPostExecute(result);
            mWeather = new Weather(result);
            refreshView(mWeather);
        }

    }

    public void refreshView(Weather mWeather){
        curPlaceTv.setText(mWeather.getCurPlace());
        curTemperatureTv.setText(mWeather.getCurTemperature());
        qualityTv.setText(mWeather.getQuality());
        curWindTv.setText(mWeather.getCurWind());
        curHumidityTv.setText(mWeather.getCurHumidity());
        curDayTv.setText(mWeather.getCurDay());

        curPmTv.setText(mWeather.getCurPm());
        curWeatherTv.setText(mWeather.getCurDayWeather());

        curDate2WindTv.setText(mWeather.getCurDate2Wind());
        Log.i(TAG,"date2:"+mWeather.getCurDate2Wind());
        curDate2WeatherTv.setText(mWeather.getCurDate2Weather());
        curDate2TempTv.setText(mWeather.getCurDate2Temp());
        curDate2Tv.setText(mWeather.getCurDate2());

        curDate3WindTv.setText(mWeather.getCurDate3Wind());
        curDate3WeatherTv.setText(mWeather.getCurDate3Weather());
        curDate3TempTv.setText(mWeather.getCurDate3Temp());
        curDate3Tv.setText(mWeather.getCurDate3());

        curDate4WindTv.setText(mWeather.getCurDate4Wind());
        curDate4WeatherTv.setText(mWeather.getCurDate4Weather());
        curDate4TempTv.setText(mWeather.getCurDate4Temp());
        curDate4Tv.setText(mWeather.getCurDate4());
        weatherTitleTv.setText(cityName+"天气");
    }
    public class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.share_weather:{

                    break;
                }
                case R.id.refresh_weather:{
                    Log.i(TAG,"city:"+cityName);
                    if(cityName.equals("")){
                        return;
                    }
                    new MyAsyncTask().execute(cityName);
                    break;
                }
                case R.id.get_location:{
                    Intent intent = new Intent(MainActivity.this,ChooseCityActivity.class);
                    startActivityForResult(intent,Constant.GET_CITY);
                }
            }
        }
    }

}
