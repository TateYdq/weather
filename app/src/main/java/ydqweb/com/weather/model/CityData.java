package ydqweb.com.weather.model;

/**
 * Created by yu_daoqing on 2018/10/23.
 */

public class CityData {
    public CityData(String cityName, String cityCode) {
        this.cityName = cityName;
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    private String cityName;
    private String cityCode;
}
