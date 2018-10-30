package ydqweb.com.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

import ydqweb.com.weather.adapter.CityAdapter;
import ydqweb.com.weather.model.CityClass;
import ydqweb.com.weather.model.CityData;
import ydqweb.com.weather.model.Constant;

public class ChooseCityActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static String TAG = "ChooseCityActivity";
    private ListView cityListLv;
    private List<CityData> cityDataList = null;
    private SearchView searchCity;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final int REFRESH_VIEW = 1;
    private CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_choose_city);
            initSwipe();
            init();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    private void init() {
        cityListLv = (ListView) findViewById(R.id.city_list_lv);
        cityDataList = CityClass.getCityList();
        final Intent intent = getIntent();
        adapter = new CityAdapter(this, cityDataList);
        cityListLv.setAdapter(adapter);
        cityListLv.setTextFilterEnabled(true);
        cityListLv.requestFocusFromTouch();
        cityListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                CityData ojChildData = (CityData) parent.getItemAtPosition(position);
                SharedPreferences pref = getSharedPreferences("AppPref", Context.MODE_PRIVATE);
                //Log.i(TAG,"cityName:"+ojChildData.getCityName());
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("cityName", ojChildData.getCityName());
                editor.putString("cityCode", ojChildData.getCityCode());
                editor.apply();
                setResult(Constant.GET_CITY);
                finish();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String search = newText.trim();
        if (TextUtils.isEmpty(search)) {
            cityListLv.clearTextFilter();//搜索文本为空时，过滤设置
        } else {
            cityListLv.setFilterText(search);//设置过滤关键字
        }
        return true;
    }

    private void initSwipe() {
        searchCity =(SearchView) findViewById(R.id.search_city_sv);
        searchCity.setOnQueryTextListener(this);
        searchCity.setSubmitButtonEnabled(false);
        swipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.city_swipe_layout);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);;
        //swipeRefreshLayout.setProgressBackgroundColor(R.color.swipe_background_color);
        swipeRefreshLayout.setPadding(20,20,20,20);
        swipeRefreshLayout.setProgressViewOffset(true,100,200);
        swipeRefreshLayout.setDistanceToTriggerSync(50);
        swipeRefreshLayout.setProgressViewEndTarget(true,100);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh () {
                //刷新屏幕
                refleshList(REFRESH_VIEW);
            }
        });
    }
    private void refleshList(final int action){
        new Thread(     new Runnable() {
            @Override
            public void run() {
                //swipeRefreshLayout.setRefreshing(true);
                mHandler.sendEmptyMessageDelayed(action,100);
            }
        }).start();
    }
    //处理消息
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            cityDataList.clear();
            switch (msg.what) {
                case REFRESH_VIEW: {
                    break;
                }
                default:
                    break;
            }
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    };


}
