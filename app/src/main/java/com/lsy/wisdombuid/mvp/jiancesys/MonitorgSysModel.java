package com.lsy.wisdombuid.mvp.jiancesys;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lsy.wisdombuid.bean.DeviceData;
import com.lsy.wisdombuid.bean.MainBody;
import com.lsy.wisdombuid.bean.MonitorgSysEntity;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.WeatherDataEntity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RJYunUrl;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.request.Request_CanShu;
import com.lsy.wisdombuid.tools.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lsy on 2020/3/31
 * MODO :
 */
public class MonitorgSysModel implements MonitorgSysInterface.Model {

    private MonitorgSysInterface.Presenter presenter;
    private Context context;

    public MonitorgSysModel(MonitorgSysInterface.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getSelectStation(String section_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.selectStation, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("jiance", "" + dataString);
                Gson gson = new Gson();

                List<StationData> dataList = new ArrayList<>();

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(dataString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        StationData stationData = gson.fromJson(jsonArray.get(i).toString(), StationData.class);
                        dataList.add(stationData);
                    }
                    presenter.responseSelect(dataList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    @Override
    public void getDeviceData(String groupId) {
        OKHttpClass okHttpClass = new OKHttpClass();

        List<Request_CanShu> listcanshu = new ArrayList<>();

        listcanshu.add(new Request_CanShu("groupId", groupId));

        //设置请求类型、地址和参数
        okHttpClass.setGetCanShu(context, RJYunUrl.getDeviceData, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("jiance", "getDeviceData==" + dataString);
                Gson gson = new Gson();

                List<DeviceData.RealData> deviceDatas = new ArrayList<>();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    MainBody mainBody = gson.fromJson(dataString, MainBody.class);
                    String datalist = jsonObject.getString("data");

                    if (mainBody.getCode() == 1000) {
                        JSONArray jsonArray = new JSONArray(datalist);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            DeviceData data = gson.fromJson(jsonArray.get(i).toString(), DeviceData.class);
                            for (int j = 0; j < data.getRealTimeData().size(); j++) {
                                DeviceData.RealData realData = gson.fromJson(data.getRealTimeData().get(j).toString(), DeviceData.RealData.class);
                                deviceDatas.add(realData);
                            }
                        }
                    }

                    presenter.responseDeviceData(deviceDatas);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    /**
     * 获取物料监测系数据
     */
    @Override
    public void getMonitorgSysEntity(String section_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.materialMonitoringSystem, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                Gson gson = new Gson();
                MonitorgSysEntity monitorgSysEntity = gson.fromJson(dataString, MonitorgSysEntity.class);

                presenter.responseMonotorgSysEntity(monitorgSysEntity.getData());
                return dataString;
            }
        });
    }

    /**
     * 获取近一周的天气数据
     */
    @Override
    public void getWeatherData() {
        OKHttpClass okHttpClass = new OKHttpClass();
        okHttpClass.DoGet2(RequestURL.weatherUrl);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                Gson gson = new Gson();
                WeatherDataEntity weatherDataEntity = gson.fromJson(dataString, WeatherDataEntity.class);
                if (weatherDataEntity.getData()!=null && weatherDataEntity.getData().size()!=0 )
                presenter.responseWeatherData(weatherDataEntity.getData().get(0));
                return dataString;
            }
        });
    }

}
