package ydqweb.com.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ydqweb.com.weather.adapter.CityAdapter;
import ydqweb.com.weather.model.CityClass;
import ydqweb.com.weather.model.CityData;
import ydqweb.com.weather.model.Constant;

public class ChooseCityActivity extends AppCompatActivity {
    private static String TAG = "ChooseCityActivity";
    private ListView cityListLv;
    private ArrayList<CityData> cityDataList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        init();
    }
    private void init(){
        cityListLv = (ListView)findViewById(R.id.city_list_lv);
        cityDataList = CityClass.getCityList();
        final Intent intent = getIntent();
        cityListLv.setAdapter(new CityAdapter(this,cityDataList));
        cityListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                CityData ojChildData= cityDataList.get(position);
                SharedPreferences pref = getSharedPreferences("AppPref", Context.MODE_PRIVATE);
                //Log.i(TAG,"cityName:"+ojChildData.getCityName());
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("cityName",ojChildData.getCityName());
                editor.putString("cityCode",ojChildData.getCityCode());
                editor.apply();
                setResult(Constant.GET_CITY);
                finish();
            }
        });
    }



}
