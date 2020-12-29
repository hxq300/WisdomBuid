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
import com.lsy.wisdombuid.activity.safety.AfterRectificationActivity;
import com.lsy.wisdombuid.adapter.AfterRectificationAdapter;
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
 * Created by lsy on 2020/4/10
 * todo : 质量待整改
 */
public class WaitReActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private AfterRectificationAdapter listAdapter;

    private int pageNo = 1;
    private int station_id = 0;//
    private List<IRecordData> dataList = new ArrayList<>();

    private LinearLayout noData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_rectification);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        Intent intent = getIntent();

        station_id = intent.getIntExtra("stationnId", 0);

        initView();


        //获取待整改数据
        getAfterRectification();
    }

    private void initView() {
        noData = findViewById(R.id.line_no_data);

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_daizhenggai);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WaitReActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);
    }


    //检查记录
    private void getAfterRectification() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        {"pageNo":1,"pageSize":10,"section_id":2}
        listcanshu.put("pageNo", pageNo);
        listcanshu.put("pageSize", 10);
        listcanshu.put("section_id", OKHttpClass.getToken(WaitReActivity.this));//标段ID登录返回
        listcanshu.put("station_id", station_id);//站点ID(下拉框里面选的)

        L.log("record", "station_id===" + OKHttpClass.getToken(WaitReActivity.this));

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(WaitReActivity.this, RequestURL.findQualityshow_DZG, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("safetyInspectionRecord", "record=待整改findQualityshow_DZG=" + dataString);
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
                        listAdapter = new AfterRectificationAdapter(WaitReActivity.this, dataList,2);
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

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.after_rectification));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getAfterRectification();
    }
}
