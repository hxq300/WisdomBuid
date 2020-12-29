package com.lsy.wisdombuid.mvp.jiancesys;

import android.content.Context;

import com.lsy.wisdombuid.bean.DeviceData;
import com.lsy.wisdombuid.bean.MonitorgSysEntity;
import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.WeatherDataEntity;
import com.lsy.wisdombuid.mvp.safety.SafetySystemInterface;
import com.lsy.wisdombuid.mvp.safety.SafetySystemModel;

import java.util.List;

/**
 * Created by lsy on 2020/3/31
 * MODO :
 */
public class MonitorgSysPresent implements MonitorgSysInterface.Presenter {

    private MonitorgSysInterface.View view;
    private MonitorgSysInterface.Model model;
    private Context context;

    public MonitorgSysPresent(MonitorgSysInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new MonitorgSysModel(this, context);
    }

    @Override
    public void getSelectStation(String section_id) {
        model.getSelectStation(section_id);
        model.getMonitorgSysEntity(section_id);
        model.getWeatherData();
    }

    @Override
    public void getDeviceData(String groupId) {
        model.getDeviceData(groupId);
    }




    @Override
    public void responseSelect(List<StationData> dataList) {
        view.setSelect(dataList);
    }

    @Override
    public void responseDeviceData(List<DeviceData.RealData> deviceData) {
        view.setDeviceData(deviceData);
    }

    @Override
    public void responseMonotorgSysEntity(MonitorgSysEntity.DataBean dataBeans) {
        view.setMonotorgSysEntity(dataBeans);
    }

    @Override
    public void responseWeatherData(WeatherDataEntity.DataBean weatherData) {
        if (weatherData!=null)
        view.setWeatherData(weatherData);
    }


    @Override
    public void distory() {
        view = null;
    }
}
