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
        int dateIndex = 0;
        int typeIndex = 0;
        HashMap<String,String> hashMap = new HashMap<>();
        try{
            XmlPullParserFactory fac = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = fac.newPullParser();
            xmlPullParser.setInput(new StringReader(xml));
            int eventType = xmlPullParser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                //Log.i(TAG,"name:"+xmlPullParser.getName());
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:{
                        break;
                    }
                    case XmlPullParser.START_TAG: {
                        //Log.i(TAG,"name:"+xmlPullParser.getName()+"text:"+xmlPullParser.getText());
                        if (xmlPullParser.getName().equals("updatetime")) {
                            eventType = xmlPullParser.next();
                            hashMap.put("updatetime", xmlPullParser.getText() + "");
                        } else if (xmlPullParser.getName().equals("city")) {
                            eventType = xmlPullParser.next();
                            hashMap.put("city", xmlPullParser.getText() + "");
                        } else if (xmlPullParser.getName().equals("shidu")) {
                            eventType = xmlPullParser.next();
                            hashMap.put("shidu", xmlPullParser.getText() + "");
                        } else if (xmlPullParser.getName().equals("wendu")) {
                            eventType = xmlPullParser.next();
                            hashMap.put("wendu", xmlPullParser.getText() + "");
                        } else if (xmlPullParser.getName().equals("fengxiang")) {
                            eventType = xmlPullParser.next();
                            hashMap.put("fengxiang", xmlPullParser.getText() + "");
                        } else if (xmlPullParser.getName().equals("quality")) {
                            eventType = xmlPullParser.next();
                            hashMap.put("quality", xmlPullParser.getText() + "");
                        } else if (xmlPullParser.getName().equals("pm25")) {
                            eventType = xmlPullParser.next();
                            hashMap.put("pm25", xmlPullParser.getText() + "");
                        } else if (xmlPullParser.getName().equals("date")) {
                            dateIndex++;
                            eventType = xmlPullParser.next();
                            switch (dateIndex) {
                                case 1: {
                                    hashMap.put("date1", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 2: {
                                    hashMap.put("date2", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 3: {
                                    hashMap.put("date3", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 4: {
                                    hashMap.put("date4", xmlPullParser.getText() + "");
                                    break;
                                }
                            }
                        } else if (xmlPullParser.getName().equals("high")) {
                            eventType = xmlPullParser.next();
                            switch (dateIndex) {
                                case 1: {
                                    hashMap.put("date1TempHigh", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 2: {
                                    hashMap.put("date2TempHigh", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 3: {
                                    hashMap.put("date3TempHigh", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 4: {
                                    hashMap.put("date4TempHigh", xmlPullParser.getText() + "");
                                    break;
                                }
                            }
                        } else if (xmlPullParser.getName().equals("fengli")) {
                            //Log.i(TAG, "typeindex:" + typeIndex);
                            eventType = xmlPullParser.next();
                            switch (typeIndex) {
                                case 1: {
                                    hashMap.put("date1DayFl", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 2: {
                                    hashMap.put("date1NightFl", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 3: {
                                    hashMap.put("date2DayFl", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 4: {
                                    hashMap.put("date2NightFl", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 5: {
                                    hashMap.put("date3DayFl", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 6: {
                                    hashMap.put("date3NightFl", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 7: {
                                    hashMap.put("date4DayFl", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 8: {
                                    hashMap.put("date4NightFl", xmlPullParser.getText() + "");
                                    break;
                                }
                            }
                        } else if (xmlPullParser.getName().equals("low")) {
                            eventType = xmlPullParser.next();
                            switch (dateIndex) {
                                case 1: {
                                    hashMap.put("date1TempLow", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 2: {
                                    hashMap.put("date2TempLow", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 3: {
                                    hashMap.put("date3TempLow", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 4: {
                                    hashMap.put("date4TempLow", xmlPullParser.getText() + "");
                                    break;
                                }
                            }
                        } else if (xmlPullParser.getName().equals("type")) {
                            eventType = xmlPullParser.next();
                            typeIndex++;
                            switch (typeIndex) {
                                case 1: {
                                    hashMap.put("date1DayWea", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 2: {
                                    hashMap.put("date1NightWea", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 3: {
                                    hashMap.put("date2DayWea", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 4: {
                                    hashMap.put("date2NightWea", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 5: {
                                    hashMap.put("date3DayWea", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 6: {
                                    hashMap.put("date3NightWea", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 7: {
                                    hashMap.put("date4DayWea", xmlPullParser.getText() + "");
                                    break;
                                }
                                case 8: {
                                    hashMap.put("date4NightWea", xmlPullParser.getText() + "");
                                    break;
                                }
                            }
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
