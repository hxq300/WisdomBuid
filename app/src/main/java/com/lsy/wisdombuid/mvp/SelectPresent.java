package com.lsy.wisdombuid.mvp;

import android.content.Context;

import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.StationData;

import java.util.List;

/**
 * Create by lsy on 2019/12/06
 * MODO : 获取分类
 */
public class SelectPresent implements SelectInterface.Presenter {

    private SelectInterface.View view;
    private SelectInterface.Model model;
    private Context context;

    public SelectPresent(SelectInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new SelectModel(this, context);
    }

    @Override
    public void getSelectStation(String section_id) {
        model.getSelectStation(section_id);
    }

    @Override
    public void getPersonCount(String section_id, String station_id) {
        model.getPersonCount(section_id, station_id);
    }

    @Override
    public void responseSelect(List<StationData> dataList) {
        view.setSelect(dataList);
    }

    @Override
    public void responsePersonCount(PMData pmData) {
        view.setPersonCount(pmData);
    }

    @Override
    public void distory() {
        view = null;
    }
}
