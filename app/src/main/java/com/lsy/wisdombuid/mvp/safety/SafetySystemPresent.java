package com.lsy.wisdombuid.mvp.safety;

import android.content.Context;

import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.SafetyIndexData;
import com.lsy.wisdombuid.bean.StationData;

import java.util.List;

/**
 * Created by lsy on 2020/3/31
 * MODO : 安全管理系统
 */
public class SafetySystemPresent implements SafetySystemInterface.Presenter {

    private SafetySystemInterface.View view;
    private SafetySystemInterface.Model model;
    private Context context;

    public SafetySystemPresent(SafetySystemInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new SafetySystemModel(this, context);
    }

    @Override
    public void getSelectStation(String section_id) {
        model.getSelectStation(section_id);
    }

    @Override
    public void getSafetyZGL(String section_id, String station_id) {
        model.getSafetyZGL(section_id, station_id);
    }

    @Override
    public void getQMS(String section_id, String station_id) {
        model.getQMS(section_id, station_id);
    }


    @Override
    public void responseSelect(List<StationData> dataList) {
        view.setSelect(dataList);
    }

    @Override
    public void responseSafetyZGL(SafetyIndexData safetyZGL) {
        view.setSafetyZGL(safetyZGL);
    }


    @Override
    public void distory() {
        view = null;
    }
}
