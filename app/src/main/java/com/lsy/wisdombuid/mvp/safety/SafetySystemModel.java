package com.lsy.wisdombuid.mvp.safety;

import android.content.Context;

import com.google.gson.Gson;
import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.SafetyIndexData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
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
 * MODO : 安全管理系统
 */
public class SafetySystemModel implements SafetySystemInterface.Model {

    private SafetySystemInterface.Presenter presenter;
    private Context context;

    public SafetySystemModel(SafetySystemInterface.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getSelectStation(String section_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);

        L.log("SafetySystem", "站点==" + section_id);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.selectStation, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("SafetySystem", "站点==" + dataString);
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
    public void getSafetyZGL(String section_id, String station_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);
        listcanshu.put("station_id", station_id);//下拉框中站点对应的ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.safetyZGL, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("SafetySystem", "safetyZGL==" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

//                    JSONObject dataObject = new JSONObject(jsonObject.get("data").toString());

                    String data = jsonObject.getString("data");

                    SafetyIndexData safetyIndexData = gson.fromJson(data, SafetyIndexData.class);


//                    String message = jsonObject.getString("message");
                    presenter.responseSafetyZGL(safetyIndexData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    //获取质量管理相关数据
    @Override
    public void getQMS(String section_id, String station_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);
        listcanshu.put("station_id", station_id);//下拉框中站点对应的ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.countQualityshow, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("SafetySystem", "countQualityshow==" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

//                    JSONObject dataObject = new JSONObject(jsonObject.get("data").toString());

                    String data = jsonObject.getString("data");

                    SafetyIndexData safetyIndexData = gson.fromJson(data, SafetyIndexData.class);


//                    String message = jsonObject.getString("message");
                    presenter.responseSafetyZGL(safetyIndexData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

}
