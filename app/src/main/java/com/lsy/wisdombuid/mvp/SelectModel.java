package com.lsy.wisdombuid.mvp;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lsy.wisdombuid.activity.PersonnelManagementActivity;
import com.lsy.wisdombuid.bean.PMData;
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
 * Create by lsy on 2019/12/06
 * MODO :
 */
public class SelectModel implements SelectInterface.Model {

    private SelectInterface.Presenter presenter;
    private Context context;

    public SelectModel(SelectInterface.Presenter presenter, Context context) {
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
                L.log("persinalSelectStation", "" + dataString);
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
    public void getPersonCount(String section_id, String station_id) {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);
        listcanshu.put("station_id", station_id);//下拉框中站点对应的ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.personCount, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("persinalPersonCount", "" + dataString);
                Gson gson = new Gson();

//
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    PMData pmData = gson.fromJson(dataString, PMData.class);
//                    String data = jsonObject.getString("data");
//                    String message = jsonObject.getString("message");
                    presenter.responsePersonCount(pmData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });

    }



//    @Override
//    public void getCollection(String id,int type) {
//        Map<String, Object> listcanshu = new HashMap<>();
//        OKHttpClass okHttpClass = new OKHttpClass();
//
////        accountId (integer, optional): 用户ID ,
////                refId (integer, optional): 需要关注或取消关注的id ,
////                type (integer, optional): 类型 0：收藏商品 1：关注店铺 2：点赞论坛 3：点赞评论 10 加好友
//        listcanshu.put("accountId", ""+OKHttpClass.getToken(context));
//        listcanshu.put("refId", ""+id);
//        listcanshu.put("type", ""+type);
//
//        //设置请求类型、地址和参数
//        okHttpClass.setPostCanShu(context, RequestURL.collection, listcanshu);
//        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
//            @Override
//            public String requestData(String dataString) {
//                //请求成功数据回调
//                L.log("collection", "collection====" + dataString);
//                Gson gson = new Gson();
//
//                MainBody mainBody = gson.fromJson(dataString, MainBody.class);
//                if (mainBody.getRtnCode() == 0) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(dataString);
//                        boolean collection=jsonObject.getBoolean("result");
//                        presenter.responseCollection(mainBody.getRtnCode(),collection);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                } else {
//                    presenter.responseCollection(mainBody.getRtnCode(),false);
//                    Toast.makeText(context, "" + mainBody.getRtnMsg(), Toast.LENGTH_SHORT).show();
//                }
//                return dataString;
//            }
//        });
//    }
}
