package ydqweb.com.weather.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ydqweb.com.weather.R;
import ydqweb.com.weather.model.CityData;

/**
 * Created by yu_daoqing on 2018/10/23.
 */

public class CityAdapter extends BaseAdapter implements Filterable {
    List<CityData> mData = null;
    Context mContext = null;
    private TextView cityName,cityCode;
    List<CityData> backData;//用来备份原始数据
    MyFilter mFilter ;
    public CityAdapter(Context context,List<CityData> data){
        this.mContext = context;
        this.mData = data;
        this.backData = data;

    }
    @Override
    public int getCount() {
        try {
            return mData.size();
        }catch (Exception ee){
            return 0;
        }
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

        View row=convertView;
        ViewHolder holder=null;

        if(row==null){
            //如果View控件还没有创建，则实例化它
            LayoutInflater inflater=LayoutInflater.from(mContext);
            row=inflater.inflate(R.layout.city_item,parent,false);
            //创建ViewHolder对象
            holder=new ViewHolder();
            //使用ViewHolder对象保存各个控件引用
            holder.cityCode=(TextView)row.findViewById(R.id.city_code_list);
            holder.cityName=(TextView)row.findViewById(R.id.city_name_list);
            row.setTag(holder);
        }
        else{
            holder=(ViewHolder)row.getTag();
        }

        //从数据源中提取数据并显示
        CityData cityData=mData.get(position);
        holder.cityName.setText(cityData.getCityName());
        holder.cityCode.setText(cityData.getCityCode()+"");
        return row;
    }
    //ViewHolder内部类，它的实例用于缓存View控件
    private static class ViewHolder{
        TextView cityName;
        TextView cityCode;
    }
    @Override
    public Filter getFilter() {
        if (mFilter ==null){
            mFilter = new MyFilter();
        }
        return mFilter;
    }
    //我们需要定义一个过滤器的类来定义过滤规则
    class MyFilter extends Filter {
        //我们在performFiltering(CharSequence charSequence)这个方法中定义过滤规则
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults result = new FilterResults();
            List<CityData> list;
            if (TextUtils.isEmpty(charSequence)) {//当过滤的关键字为空的时候，我们则显示所有的数据
                list = backData;
            } else {//否则把符合条件的数据对象添加到集合中
                list = new ArrayList<>();
                for (CityData bd : backData) {
                    if ((bd.getCityName()!= null && bd.getCityName().contains(charSequence)) || (bd.getCityCode() != null && bd.getCityCode().contains(charSequence))) {
                        //LogUtil.d("performFiltering:"+recomend.toString());
                        list.add(bd);
                    }

                }
                Log.i("Main","change:"+list.size());
            }
            result.values = list; //将得到的集合保存到FilterResults的value变量中
            result.count = list.size();//将集合的大小保存到FilterResults的count变量中

            return result;
        }

        //在publishResults方法中告诉适配器更新界面
        @Override
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            mData = (List<CityData>) filterResults.values;
            //LogUtil.d("publishResults:"+filterResults.count);
            if (filterResults.count > 0) {
                notifyDataSetChanged();//通知数据发生了改变
                //LogUtil.d("publishResults:notifyDataSetChanged");
            } else {
                notifyDataSetInvalidated();//通知数据失效
                //LogUtil.d("publishResults:notifyDataSetInvalidated");
            }
        }
    }

}
