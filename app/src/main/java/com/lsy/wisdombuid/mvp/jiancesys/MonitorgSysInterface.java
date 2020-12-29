package com.lsy.wisdombuid.mvp.jiancesys;

import com.lsy.wisdombuid.bean.DeviceData;
import com.lsy.wisdombuid.bean.MonitorgSysEntity;
import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.WeatherDataEntity;

import java.util.List;

/**
 * Created by lsy on 2020/4/2
 * todo :
 */
public interface MonitorgSysInterface {

    interface Model {
        void getSelectStation(String section_id);//section_id部门id

        void getDeviceData(String groupId);//获取设备信息及实时数据接口

        void getMonitorgSysEntity(String section_id);//获取最近一条扬尘检测仪数据后回调

        void getWeatherData(); //获取近一周的天气数据
    }

    interface View {
        void setSelect(List<StationData> dataList);//设置站点

        void setDeviceData(List<DeviceData.RealData> deviceData);//

        void setMonotorgSysEntity(MonitorgSysEntity.DataBean dataBean);//view更新ui

        void setWeatherData(WeatherDataEntity.DataBean weatherData); // 设置当天的天气详情
    }

    interface Presenter {
        void getSelectStation(String section_id);//section_id

        void getDeviceData(String groupId);


        //获取数据以后回调
        void responseSelect(List<StationData> dataList);

        void responseDeviceData(List<DeviceData.RealData> deviceData);
        //获取最近一条扬尘检测仪数据后回调
        void responseMonotorgSysEntity(MonitorgSysEntity.DataBean dataBean);
        //获取近一周天气情况的回调
        void responseWeatherData(WeatherDataEntity.DataBean weatherData);

        void distory();
    }

}
