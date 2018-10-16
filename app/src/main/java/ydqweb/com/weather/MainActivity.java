package ydqweb.com.weather;

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

import ydqweb.com.weather.model.Weather;
import ydqweb.com.weather.model.XML;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private TextView curTemperatureTv, curPlaceTv,qualityTv,curHumidityTv,curDayTv,curWeatherTv,curWindTv,curPmTv;
    private ImageButton refreshBtn,shareBtn;
    private Weather mWeather;
    private static final int GET_VALUE = 1;
    String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        Toolbar toolbar =  (Toolbar) findViewById(R.id.main_toolbar);
        curPlaceTv = (TextView)findViewById(R.id.cur_place);
        curTemperatureTv = (TextView)findViewById(R.id.cur_temperature);
        curHumidityTv = (TextView)findViewById(R.id.cur_humidity);
        curDayTv = (TextView)findViewById(R.id.cur_day);
        curWeatherTv = (TextView)findViewById(R.id.cur_weather);
        curWindTv = (TextView)findViewById(R.id.cur_wind);
        qualityTv = (TextView)findViewById(R.id.cur_quality);
        refreshBtn = (ImageButton)findViewById(R.id.refresh_weather);

        refreshBtn.setOnClickListener(new MyButtonListener());
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
        curTemperatureTv.setText(mWeather.getCurPm());
        qualityTv.setText(mWeather.getQuality());
        curWindTv.setText(mWeather.getCurWind());
        curHumidityTv.setText(mWeather.getCurHumidity());
        curDayTv.setText(mWeather.getCurDay());
        curPmTv.setText(mWeather.getCurPm());

    }
    public class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.share_weather:{

                    break;
                }
                case R.id.refresh_weather:{
                    new MyAsyncTask().execute("北京");
                    break;
                }
            }
        }
    }

}
