package com.lsy.wisdombuid.mvp.upload;

import android.content.Context;

import com.lsy.wisdombuid.bean.GongXuData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.UploadData;
import com.lsy.wisdombuid.bean.UtilsData;

import java.util.List;

/**
 * Created by lsy on 2020/4/12
 * todo :
 */
public class UploadPresent implements UploadInterface.Presenter {

    private UploadInterface.View view;
    private UploadInterface.Model model;
    private Context context;

    public UploadPresent(UploadInterface.View view, Context context) {
        this.view = view;
        this.model = new UploadModel(this, context);
        this.context = context;
    }

    @Override
    public void getSelectStation(String section_id) {
        model.getSelectStation(section_id);
    }

    @Override
    public void getGongXu(String section_id, String station_id) {
        model.getGongXu(section_id, station_id);
    }

    @Override
    public void getUtils(String section_id, String station_id) {
        model.getUtils(section_id, station_id);
    }

    @Override
    public void getAnquan(String section_id, String station_id) {
        model.getAnquanType(section_id, station_id);
    }

    @Override
    public void getZhiliang(String section_id, String station_id) {
        model.getZhiliang(section_id, station_id);
    }

    @Override
    public void addAnqaun(String title, int risk_id, int section_id, int station_id, int sub_id, String description, String url1, int staff_id, int process_id, String responsible) {
        model.addAnqaun(title, risk_id, section_id, station_id, sub_id, description, url1, staff_id, process_id, responsible);
    }

    @Override
    public void addZhiliang(String title, int quality_id, int section_id, int station_id, int sub_id, String description, String url1, int staff_id, int process_id, String responsible) {
        model.addZhiliang(title, quality_id, section_id, station_id, sub_id, description, url1, staff_id, process_id, responsible);
    }

    @Override
    public void responseSelect(List<UploadData> dataList) {
        view.setSelect(dataList);
    }

    @Override
    public void responseGongXue(List<UploadData> dataList) {
        view.setGongXu(dataList);
    }

    @Override
    public void responseUtils(List<UploadData> dataList) {
        view.setUtils(dataList);
    }

    @Override
    public void responseAnquan(List<UploadData> dataList) {
        view.setType(dataList);
    }

    @Override
    public void responseZhiliang(List<UploadData> dataList) {
        view.setType(dataList);
    }

    @Override
    public void responseSucess() {
        view.setSucess();
    }

    @Override
    public void distory() {
        view = null;
    }
}
