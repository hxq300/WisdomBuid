package com.lsy.wisdombuid.activity.quality;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.safety.RectificationNoticeActivity;
import com.lsy.wisdombuid.adapter.RectNoticeAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/4/10
 * todo : 质量整改通知单
 */
public class QMSNoticeActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private RectNoticeAdapter listAdapter;

    //===标段
    private SharedUtils sharedUtils;
    private IRecordData recordData;

    private TextView reStation, reUpdate, reGongxu, reTongzhi, rePlanTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qms_notice);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        sharedUtils = new SharedUtils(QMSNoticeActivity.this, SharedUtils.WISDOM);

        //初始化view
        initView();

        //初始化数据
        initData();

    }

    private void initView() {

        reStation = findViewById(R.id.renotice_station);
        reUpdate = findViewById(R.id.renotice_update);
        reGongxu = findViewById(R.id.renotice_gongxu);
        reTongzhi = findViewById(R.id.renotice_tognzhi);
        rePlanTime = findViewById(R.id.renotice_plan_time);

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_zhenggaitongzhidan);
        idListRecycle.setItemViewCacheSize(100);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RectificationNoticeActivity.this);
        idListRecycle.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        idListRecycle.setNestedScrollingEnabled(false);
    }

    private void initData() {
        recordData = new IRecordData();
        Gson gson = new Gson();

        String data = sharedUtils.getData(SharedUtils.IRDETAILS, "");

        if (data != "" || !data.equals("")) {
            recordData = gson.fromJson(sharedUtils.getData(SharedUtils.IRDETAILS, ""), IRecordData.class);

//            reStation, reUpdate, reGongxu, reTongzhi, rePlanTime;

            reStation.setText(" " + recordData.getStation_name());
            reUpdate.setText("" + recordData.getUptime());
            reGongxu.setText("" + recordData.getProcess_name());
            reTongzhi.setText("经查，你单位在" + recordData.getStation_name() + "建设中存在下列问题:" +
                    "请按照智慧工地建设标准进行整改，于" + recordData.getPlan_time() + "日前完成整改。逾期不整改的将进行相应处罚。\n" +
                    "特此通知。");
            rePlanTime.setText(" " + recordData.getPlan_time());

        } else {
            ToastUtils.showBottomToast(QMSNoticeActivity.this, "获取数据失败");
        }

        List<String> images = new ArrayList<>();

        L.log("record", "" + recordData.getUrl());
        if (recordData.getUrl() != null) {
            try {
                JSONArray jsonArray = new JSONArray("" + recordData.getUrl());

                for (int i = 0; i < 2; i++) {

                    images.add(jsonArray.get(i).toString());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        listAdapter = new RectNoticeAdapter(QMSNoticeActivity.this, images);
        idListRecycle.setAdapter(listAdapter);
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.RectificationNotice));
        }
    }


}
