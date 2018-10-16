package ydqweb.com.weather.model;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by yu_daoqing on 2018/10/9.
 */

public class XML {
    private static String TAG = "XML";
    public static HashMap<String,String> dealXML(String xml){
        int dateIndex = 1;
        HashMap<String,String> hashMap = new HashMap<>();
        try{
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = fac.newPullParser();
            xmlPullParser.setInput(new StringReader(xml));
            int eventType = xmlPullParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:{
                        break;
                    }
                    case XmlPullParser.START_TAG:{
                        //Log.i(TAG,"name:"+xmlPullParser.getName()+"text:"+xmlPullParser.getText());
                        if(xmlPullParser.getName().equals("updatetime")){
                            eventType = xmlPullParser.next();
                            hashMap.put("updatetime",xmlPullParser.getText());
                        }else if(xmlPullParser.getName().equals("city")){
                            eventType = xmlPullParser.next();
                            hashMap.put("city",xmlPullParser.getText());
                        }else if(xmlPullParser.getName().equals("fengxiang")){
                            eventType = xmlPullParser.next();
                            hashMap.put("fengxiang",xmlPullParser.getText());
                        }else if(xmlPullParser.getName().equals("quality")){
                            eventType = xmlPullParser.next();
                            hashMap.put("quality",xmlPullParser.getText());
                        }else if(xmlPullParser.getName().equals("pm25")){
                            eventType = xmlPullParser.next();
                            hashMap.put("pm25",xmlPullParser.getText());
                        }else if(xmlPullParser.getName().equals("fengli")){
                            eventType = xmlPullParser.next();
                            hashMap.put("fengli",xmlPullParser.getText());
                        }else if(xmlPullParser.getName().equals("data")){
                            eventType = xmlPullParser.next();
                            switch (dateIndex){
                                case 1:{
                                    hashMap.put("date1", xmlPullParser.getText());
                                    break;
                                }
                                case 2:{
                                    hashMap.put("date2", xmlPullParser.getText());
                                    break;
                                }
                                case 3:{
                                    hashMap.put("date3", xmlPullParser.getText());
                                    break;
                                }
                                case 4:{
                                    hashMap.put("date4", xmlPullParser.getText());
                                    break;
                                }
                            }
                            dateIndex++;
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        break;
                    }
                }
                eventType = xmlPullParser.next();
            }
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
        return hashMap;
    }
}
