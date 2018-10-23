package ydqweb.com.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ydqweb.com.weather.R;
import ydqweb.com.weather.model.CityData;

/**
 * Created by yu_daoqing on 2018/10/23.
 */

public class CityAdapter extends BaseAdapter {
    List<CityData> mData = null;
    Context mContext = null;
    private TextView cityName,cityCode;
    public CityAdapter(Context context,List<CityData> data){
        mContext = context;
        mData = data;

    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = null;
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            rootView = layoutInflater.inflate(R.layout.city_item,parent,false);

        }  else {
            rootView = convertView;
        }
        cityName = rootView.findViewById(R.id.city_name_list);
        cityCode = rootView.findViewById(R.id.city_code_list);
        CityData item = mData.get(position);
        cityName.setText(item.getCityName());
        cityCode.setText(item.getCityCode());
        return rootView;
    }
}
