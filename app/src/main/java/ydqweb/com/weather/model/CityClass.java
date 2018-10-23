package ydqweb.com.weather.model;

import java.util.ArrayList;

/**
 * Created by yu_daoqing on 2018/10/23.
 */

public class CityClass {
    public static ArrayList<CityData> getCityList(){
        return getStaticCityList();
    }
    public static ArrayList<CityData> getStaticCityList(){
        ArrayList<CityData> cds = new ArrayList<CityData>();
        CityData ci1 = new CityData("北京","1010");
        CityData ci2 = new CityData("上海","1011");
        cds.add(ci1);
        cds.add(ci2);
        return cds;
    }

}
