package com.lsy.wisdombuid.activity.safety;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.InspectionRecordAdapter;
import com.lsy.wisdombuid.adapter.ZhengGaiImgAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.bean.RectifyEntity;
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
 * Created by lsy on 2020/3/30
 * todo : 检查记录(无效记录)
 */
public class InspectionRecordActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private InspectionRecordAdapter listAdapter;

    private LinearLayout noData;

    private int station_id = 0;//
    private int intId = 1;//1\查询记录  2、无效数据a
    private String title;
    private int pageNo = 1;

    private List<RectifyEntity.ItemsBean> dataList = new ArrayList<>();


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
        intId = intent.getIntExtra("intId", 1);
        title = intent.getStringExtra("title");

        initTitle();


        L.log("record", "intId===" + intId);

        //初始化view
        initView();

        if (intId == 1) {
            //获取检查记录
            getRecord();
        } else {
            //获取无效数据
            getInvalidRecords();
        }


    }


    //检查记录
    private void getRecord() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        {"pageNo":1,"pageSize":10,"section_id":2}
        listcanshu.put("pageNo", pageNo);
        listcanshu.put("pageSize", 10);
        listcanshu.put("section_id", OKHttpClass.getToken(InspectionRecordActivity.this));//标段ID登录返回
        listcanshu.put("station_id", station_id);//站点ID(下拉框里面选的)

        L.log("record", "station_id===" + OKHttpClass.getToken(InspectionRecordActivity.this));

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(InspectionRecordActivity.this, RequestURL.safetyInspectionRecord, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("safetyInspectionRecord", "record==" + dataString);

                Gson gson = new Gson();
                RectifyEntity rectifyEntity = gson.fromJson(dataString, RectifyEntity.class);
                dataList.addAll(rectifyEntity.getItems());


                if (dataList != null && dataList.size() > 0) {
                    listAdapter = new InspectionRecordAdapter(InspectionRecordActivity.this, dataList, 1);
                    idListRecycle.setAdapter(listAdapter);
                    noData.setVisibility(View.GONE);
                } else {
                    noData.setVisibility(View.VISIBLE);
                }

                return dataString;
            }
        });

    }

    //无效记录
    private void getInvalidRecords() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        {"pageNo":1,"pageSize":10,"section_id":2}
        listcanshu.put("pageNo", pageNo);
        listcanshu.put("pageSize", 10);
        listcanshu.put("section_id", OKHttpClass.getToken(InspectionRecordActivity.this));//标段ID登录返回
        listcanshu.put("station_id", station_id);//站点ID(下拉框里面选的)

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(InspectionRecordActivity.this, RequestURL.safetyInvalidRecords, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                Gson gson = new Gson();
                RectifyEntity rectifyEntity = gson.fromJson(dataString, RectifyEntity.class);
                dataList.addAll(rectifyEntity.getItems());

                if (dataList != null && dataList.size() > 0) {
                    listAdapter = new InspectionRecordAdapter(InspectionRecordActivity.this, dataList, 2);
                    idListRecycle.setAdapter(listAdapter);
                    noData.setVisibility(View.GONE);
                } else {
                    noData.setVisibility(View.VISIBLE);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InspectionRecordActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + title);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getRecord();
    }
}
