package com.lsy.wisdombuid.activity.quality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.InspectionRecordAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/4/12
 * todo : 整改复查列表
 */
public class QMSReviewListActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private InspectionRecordAdapter listAdapter;

    private LinearLayout noData;

    private int station_id = 0;//
    private int intId = 1;//1\查询记录  2、无效数据a
    private int pageNo = 1;

    private List<IRecordData> dataList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_record);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);


        Intent intent = getIntent();

        station_id = intent.getIntExtra("stationnId", 0);

        initTitle();


        L.log("record", "intId===" + intId);

        //初始化view
        initView();

        getRecord();

    }

    //检查记录
    private void getRecord() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        {"pageNo":1,"pageSize":10,"section_id":2,"station_id":1}
        listcanshu.put("pageNo", pageNo);
        listcanshu.put("pageSize", 10);
        listcanshu.put("section_id", OKHttpClass.getToken(QMSReviewListActivity.this));//标段ID登录返回
        listcanshu.put("station_id", station_id);//站点ID(下拉框里面选的)

        L.log("record", "station_id===" + OKHttpClass.getToken(QMSReviewListActivity.this));

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(QMSReviewListActivity.this, RequestURL.findQualityshow_ZGFC, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("safetyInspectionRecord", "record==" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    JSONArray jsonArray = new JSONArray(jsonObject.getString("items"));

                    dataList.clear();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        IRecordData data = gson.fromJson(jsonArray.get(i).toString(), IRecordData.class);

                        dataList.add(data);
                    }
                    if (dataList != null && dataList.size() > 0) {
                        listAdapter = new InspectionRecordAdapter(QMSReviewListActivity.this, dataList, 4);
                        idListRecycle.setAdapter(listAdapter);
                        noData.setVisibility(View.GONE);
                    } else {
                        noData.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });

    }

    private void initView() {
        noData = findViewById(R.id.line_no_data);

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_inspection_record);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QMSReviewListActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.check_the_rectification));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getRecord();
    }
}
