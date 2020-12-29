package com.lsy.wisdombuid.mvp.upload;

import android.content.Context;

import com.google.gson.Gson;
import com.lsy.wisdombuid.activity.safety.ZhengGaiReviewActivity;
import com.lsy.wisdombuid.bean.GongXuData;
import com.lsy.wisdombuid.bean.SafetyIndexData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.UploadData;
import com.lsy.wisdombuid.bean.UtilsData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/4/13
 * todo :
 */
public class UploadModel implements UploadInterface.Model {

    private UploadInterface.Presenter presenter;
    private Context context;

    public UploadModel(UploadInterface.Presenter presenter, Context context) {
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
                L.log("mine", "站点==" + dataString);
                Gson gson = new Gson();

                List<UploadData> dataList = new ArrayList<>();

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(dataString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        UploadData stationData = gson.fromJson(jsonArray.get(i).toString(), UploadData.class);
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
    public void getGongXu(String section_id, String station_id) {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);
        listcanshu.put("station_id", station_id);//下拉框中站点对应的ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.selectProcess, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("mine", "工序==" + dataString);
                Gson gson = new Gson();

                List<UploadData> dataList = new ArrayList<>();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

//                    JSONObject dataObject = new JSONObject(jsonObject.get("data").toString());

                    JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        UploadData gongXuData = gson.fromJson(jsonArray.get(i).toString(), UploadData.class);
                        dataList.add(gongXuData);
                    }


//                    String message = jsonObject.getString("message");
                    presenter.responseGongXue(dataList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });

    }

    @Override
    public void getUtils(String section_id, String station_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);
        listcanshu.put("station_id", station_id);//下拉框中站点对应的ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.selectSubcontractors, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("mine", "单位==" + dataString);
                Gson gson = new Gson();

                List<UploadData> utilsDataList = new ArrayList<>();

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(dataString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        UploadData utilsData = gson.fromJson(jsonArray.get(i).toString(), UploadData.class);
                        utilsDataList.add(utilsData);
                    }

                    presenter.responseUtils(utilsDataList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    @Override
    public void getAnquanType(String section_id, String station_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);
        listcanshu.put("station_id", station_id);//下拉框中站点对应的ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.selectRisk, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("mine", "安全==" + dataString);
                Gson gson = new Gson();

                List<UploadData> utilsDataList = new ArrayList<>();

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(dataString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        UploadData utilsData = gson.fromJson(jsonArray.get(i).toString(), UploadData.class);
                        utilsDataList.add(utilsData);
                    }

                    presenter.responseAnquan(utilsDataList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    @Override
    public void getZhiliang(String section_id, String station_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);
        listcanshu.put("station_id", station_id);//下拉框中站点对应的ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.selectQuality, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("mine", "质量==" + dataString);
                Gson gson = new Gson();

                List<UploadData> utilsDataList = new ArrayList<>();

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(dataString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        UploadData utilsData = gson.fromJson(jsonArray.get(i).toString(), UploadData.class);
                        utilsDataList.add(utilsData);
                    }

                    presenter.responseZhiliang(utilsDataList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    @Override
    public void addAnqaun(String title, int risk_id, int section_id, int station_id, int sub_id, String description,
                          String url1, int staff_id, int process_id, String responsible) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("title", title);
        listcanshu.put("risk_id", risk_id);//
        listcanshu.put("section_id", section_id);//
        listcanshu.put("station_id", station_id);//
        listcanshu.put("sub_id", sub_id);//
        listcanshu.put("description", description);//
        listcanshu.put("url", url1);//
        listcanshu.put("staff_id", staff_id);//
        listcanshu.put("process_id", process_id);//
        listcanshu.put("responsible", responsible);//

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.insertRiskshow, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("mine", "添加安全==" + dataString);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        presenter.responseSucess();
                    } else {
                        ToastUtils.showBottomToast(context, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    @Override
    public void addZhiliang(String title, int quality_id, int section_id, int station_id, int sub_id, String description, String url1, int staff_id, int process_id, String responsible) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("title", title);
        listcanshu.put("quality_id", quality_id);//
        listcanshu.put("section_id", section_id);//
        listcanshu.put("station_id", station_id);//
        listcanshu.put("sub_id", sub_id);//
        listcanshu.put("description", description);//
        listcanshu.put("url", url1);//
        listcanshu.put("staff_id", staff_id);//
        listcanshu.put("process_id", process_id);//
        listcanshu.put("responsible", responsible);//

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.insertQualityshow, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("mine", "添加质量==" + dataString);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        presenter.responseSucess();
                    } else {
                        ToastUtils.showBottomToast(context, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }
}
